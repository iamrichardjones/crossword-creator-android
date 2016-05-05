package info.richardjones.crossword.app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
        TestCrosswordLoader loader = new TestCrosswordLoader();

        final GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setNumColumns(loader.getWidth());

        List<Cell> cells = loader.getCells();
        final CrosswordAdapter adapter = new CrosswordAdapter(EntryPoint.this, cells);
        gridView.setAdapter(adapter);

        final ListView listview = (ListView) findViewById(R.id.clueList);
        final ClueAdapter clueAdapter = new ClueAdapter(this, getCellsWithClues(cells));
        listview.setAdapter(clueAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Cell item = adapter.getItem(position);
                Log.d("ABC", "click0 "+ item.getForegroundColour());
                Log.d("ABC", "Position is2 " + position);
                if (item.getForegroundColour() == Color.WHITE) {
                    Log.d("ABC", "click1 " + item.getForegroundColour());
                    item.setForegroundColour(Color.BLACK);
                    Log.d("ABC", "click2 " + item.getForegroundColour());
                }
                adapter.notifyDataSetChanged();
                gridView.setAdapter(adapter);
                Toast.makeText(EntryPoint.this, "Position: " + position + " " + item, Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listview.smoothScrollToPosition(clueAdapter.getListPositionOfClue(adapter.getItem(position).getNumber()));
                return true;
            }
        });

    }

    private List<Cell> getCellsWithClues(List<Cell> cells) {
        List<Cell> cellsWithClues = newArrayList();

        Cell cell1 = new Cell();
        cell1.setNumber(0);

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
}
