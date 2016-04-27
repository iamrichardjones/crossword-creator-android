package info.richardjones.crossword.app;

import android.content.Context;
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

            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            if (cell.getNumber() > 0) {
                textView.setText(Html.fromHtml(String.format("<sup><small>%s</small></sup> <font color='black'>%s</font>", cell.getNumber(), cell.getLetter())));
            }
            else {
                textView.setText(Html.fromHtml(String.format("<font color='black'>%s</font>", cell.getLetter())));

            }
        } else {
            gridView = convertView;
        }

        Log.d("ABC", "Position is " + position);
        gridView.setBackgroundColor(cell.getBackgroundColour());

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