package info.richardjones.crossword.app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CrosswordAdapter extends BaseAdapter {

    private Context context;

    public CrosswordAdapter(Context context) {
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.GridCell, null);


            // set value into textview
            TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
            textView.setText("" + position);
        } else {
            gridView = convertView;
        }

        Integer gridColour = EntryPoint.GRID_COLOURS[position];
        Log.d("ABC", "" + gridColour);
        gridView.setBackgroundColor(gridColour);

        return gridView;
    }

    @Override
    public int getCount() {
        return EntryPoint.GRID_COLOURS.length;
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