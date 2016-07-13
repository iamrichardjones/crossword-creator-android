package info.richardjones.crossword.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import info.richardjones.crossword.app.loader.TestCrosswordLoader;
import info.richardjones.crossword.app.vo.Cell;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class EntryPoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_point);

        loadData();
    }



    private void loadData() {
//        final int CELL_WIDTH = 40;
        final int CELL_WIDTH = 85;   //width == 1080 height = 1776
                                     //width = 1536  height = 2048   << S2 TABLET

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;


        TestCrosswordLoader loader = new TestCrosswordLoader();

        Integer numOfColumns = loader.getWidth();
        Integer numOfRows = loader.getHeight();

        final GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setNumColumns(numOfColumns);
        gridView.getLayoutParams().width = (CELL_WIDTH * numOfColumns);
        gridView.getLayoutParams().height = (CELL_WIDTH * numOfRows);

        Log.e("WIDTH", width + " a");
        Log.e("HEIGHT", height  + " a");

        List<Cell> cells = loader.getCells();

        final CrosswordAdapter adapter = new CrosswordAdapter(EntryPoint.this, EntryPoint.this, cells);
        gridView.setAdapter(adapter);

        final ListView listview = (ListView) findViewById(R.id.clueList);
        final ClueAdapter clueAdapter = new ClueAdapter(this, getCellsWithClues(cells));
        listview.setAdapter(clueAdapter);


/*
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
//                Cell item = adapter.getItem(position);
//                Log.d("ABCD", "click0 "+ item.getForegroundColour());
                Log.d("ABCDE", "YES YES Position is2 " + position);
                Toast.makeText(EntryPoint.this, "Position: " + position, Toast.LENGTH_SHORT).show();
//                if (item.getForegroundColour() == Color.WHITE) {
//                    Log.d("ABC", "click1 " + item.getForegroundColour());
//                    item.setForegroundColour(Color.BLACK);
//                    Log.d("ABC", "click2 " + item.getForegroundColour());
//                }
//                adapter.notifyDataSetChanged();
//                gridView.setAdapter(adapter);

                // Show soft keyboard for the user to enter the value.
//                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(gridView, InputMethodManager.SHOW_IMPLICIT);

//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(tv, InputMethodManager.SHOW_IMPLICIT);
            }
        });
*/
//        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d("ABCDE", "EXPECTED Position is3 " + position);
//                Toast.makeText(EntryPoint.this, "Position: " + position, Toast.LENGTH_SHORT).show();
//                listview.smoothScrollToPosition(clueAdapter.getListPositionOfClue(adapter.getItem(position).getNumber()));
//                return true;
//            }
//        });

    }

    private List<Cell> getCellsWithClues(List<Cell> cells) {
        List<Cell> cellsWithClues = newArrayList();

        Cell cell1 = new Cell();
        cell1.setNumber(new Long(Math.round(Math.random() * 100)).intValue());

        for (int i = 0; i < 10; i++) {
            cellsWithClues.add(cell1);
        }

        for (Cell cell : cells) {
            if (cell.getNumber() > 0) {
                cellsWithClues.add(cell);
                cellsWithClues.add(cell1);
            }
        }

        Collections.sort(cellsWithClues, Cell.SORT_BY_NUMBER);

        return cellsWithClues;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_entry_point, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}
