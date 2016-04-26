package info.richardjones.crossword.app;

import android.content.Context;
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

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.grid_cell, null);

            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            if (position < cells.size()) {
                textView.setText(cells.get(position).getLetter());
            }
        } else {
            gridView = convertView;
        }

        Log.d("ABC", "Position is " + position);
        if (position < cells.size()) {
            Log.d("ABC", "" + cells.get(position).getBackgroundColour());
            gridView.setBackgroundColor(cells.get(position).getBackgroundColour());
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return cells.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}