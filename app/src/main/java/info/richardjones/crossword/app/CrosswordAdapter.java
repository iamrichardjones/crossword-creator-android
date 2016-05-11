package info.richardjones.crossword.app;

import android.content.Context;
import android.graphics.Color;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import info.richardjones.crossword.app.vo.Cell;

import java.util.List;


public class CrosswordAdapter extends BaseAdapter {

    private Context context;
    private final List<Cell> cells;

    public CrosswordAdapter(Context context, List<Cell> cells) {
        this.context = context;
        this.cells = cells;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        Cell cell = cells.get(position);

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_cell, null);

            String textColour = "#FF0000";
//            if (cell.getForegroundColour() == Color.BLACK) {
//                textColour = "#000000";
//                Log.d("ABC", "Setting to black " + cell.getLetter());
//            }

            Log.d("ABC", "Colour in adapter is " + textColour);

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
//            if (cell.getNumber() > 0) {
//                textView.setText(Html.fromHtml(String.format("<font color='%s'><sup><small>%s</small></sup> %s</font>", textColour, cell.getNumber(), cell.getLetter())));
//            }
//            else {
                textView.setText(Html.fromHtml(String.format("<font color='%s'>%s</font>", textColour, cell.getLetter())));
//            }
            TextView superScriptTv = (TextView) gridView.findViewById(R.id.grid_item_number);
            if (cell.getNumber() > 0) {
                superScriptTv.setText(Html.fromHtml(String.format("<font>%s</font>", cell.getNumber())));
            }
        } else {
            gridView = convertView;
        }

        gridView.setBackgroundColor(cell.getBackgroundColour());

        return gridView;
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
        return 0;
    }


    public void repaint() {
        notifyDataSetChanged();
    }
}