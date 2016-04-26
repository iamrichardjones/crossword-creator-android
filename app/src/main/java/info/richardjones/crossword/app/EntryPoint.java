package info.richardjones.crossword.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import info.richardjones.crossword.app.loader.TestCrosswordLoader;
import info.richardjones.crossword.app.vo.Cell;

import java.util.List;

public class EntryPoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_point);

        loadData();
    }

    private void loadData() {
        TestCrosswordLoader loader = new TestCrosswordLoader();

        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setNumColumns(loader.getWidth());

        List<Cell> cells = loader.getCells();
        CrosswordAdapter adapter = new CrosswordAdapter(EntryPoint.this, cells);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(EntryPoint.this, "Position: " + position, Toast.LENGTH_SHORT).show();
            }
        });

        loadClueList(cells);
    }

    private void loadClueList(List<Cell> cells) {
        final ListView listview = (ListView) findViewById(R.id.clueList);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, android.R.id.text1, cells);
        listview.setAdapter(adapter);
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
