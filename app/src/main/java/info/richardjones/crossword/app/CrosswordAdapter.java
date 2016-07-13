package info.richardjones.crossword.app;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import info.richardjones.crossword.app.vo.Cell;

import java.util.List;


public class CrosswordAdapter extends BaseAdapter {

    private EntryPoint entryPoint;
    private Context context;
    private final List<Cell> cells;

    public CrosswordAdapter(EntryPoint entryPoint, Context context, List<Cell> cells) {
        this.context = context;
        this.entryPoint = entryPoint;
        this.cells = cells;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        final Cell cell = cells.get(position);

        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.grid_cell, null);

            final EditText editText = (EditText) gridView.findViewById(R.id.grid_item_label);
            editText.setText(" ");

            TextView superScriptTv = (TextView) gridView.findViewById(R.id.grid_item_number);
            if (cell.getNumber() > 0) {
                superScriptTv.setText(Html.fromHtml(String.format("<font>%s</font>", cell.getNumber())));
            }

        } else {
            gridView = convertView;
        }

        AbsListView.LayoutParams params = new AbsListView.LayoutParams(80,80);
        gridView.setLayoutParams(params);

        gridView.setBackgroundColor(cell.getBackgroundColour());


        final View gv = gridView;

        final EditText editText = (EditText) gv.findViewById(R.id.grid_item_label);
        cell.setGuess(editText.getText().toString());

        Log.e("CELL", " letter " + cell.getLetter() + " guess " + cell.getGuess());

        editText.setOnClickListener(new EditText.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.e("GRID2", "letter: " + cell.getLetter() );
                if (cell.getLetter().equals("") || cell.getLetter() == null) {
                    return;
                }
                entryPoint.showKeyboard(editText);
                Log.e("GRID2", "onClick22: " + position );
            }

        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                cell.setGuess(s.toString());
                String textColour = cell.getGuess().equals(cell.getLetter()) ? "#00FF00" : "#FF0000" ;
                editText.setTextColor(Color.parseColor(textColour));

                entryPoint.closeKeyboard();
            }
        });

//        editText.setOnLongClickListener(new EditText.OnLongClickListener(){
//
//            @Override
//            public boolean onLongClick(View v) {
//                Log.e("GRID2", "onLongClick22: " + position );
//                return true;
//            }
//
//        });

        return gridView;
    }

    private void setUpClick(Integer position, EditText editText) {


            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
//
            editText.requestFocusFromTouch();
            if (editText.requestFocus()) {
                Log.e("GRID2", "req = true");
            }
            else {
                Log.e("GRID2", "req = false");
            }
            Log.e("GRID2", "is focus " + editText.isFocusable());
            Log.e("GRID2", "is focus in touch " + editText.isFocusableInTouchMode());
            Log.e("GRID2", "is focused " + editText.isFocused());

            editText.requestFocus();
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);


//            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

            editText.requestFocus();



//                                            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                                            imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);


            Log.e("GRID2", "inside method onClick: " + position );
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public Cell getItem(int position) {
        return cells.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}