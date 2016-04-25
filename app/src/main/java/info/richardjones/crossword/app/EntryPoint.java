package info.richardjones.crossword.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.google.gson.Gson;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class EntryPoint extends AppCompatActivity {

    public static final Integer[] GRID_COLOURS = {
            0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF,
            0xFF000000, 0xFFFFFFFF, 0xFF000000, 0xFF000000, 0xFF000000,
            0xFFFFFFFF, 0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF, 0xFFFFFFFF,
            0xFF000000, 0xFFFFFFFF, 0xFF000000, 0xFFFFFFFF, 0xFFFFFFFF
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_point);

        GridView gridView = (GridView) findViewById(R.id.grid_view);

        MyAdapter adapter = new MyAdapter(EntryPoint.this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(EntryPoint.this, "D" + position, Toast.LENGTH_SHORT).show();
            }
        });

        loadList();
    }

    private void loadList() {
        final ListView listview = (ListView) findViewById(R.id.clueList);
        List<String> values = newArrayList("Clue111", "Clue2", "Clue3",
                "Clue4", "Clue5", "Clue6", "Clue7", "Clue8", "Clue9");

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.activity_list_item, android.R.id.text1, values);
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
