package info.richardjones.crossword.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by jones on 19/04/2016.
 */

public class Grid extends BaseAdapter {

    private final String[] gridcolor;
    private Context mContext;

    public Grid(Context context, String[] gridcolor) {
        mContext = context;
        this.gridcolor=gridcolor;
    }

    @Override
    public int getCount() {
        return gridcolor.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            grid = new View(mContext);
//            grid = inflater.inflate(R.layout.activity_entry_point, null);
            grid.setBackgroundColor(Color.parseColor(gridcolor[i]));
        }
        else {
            grid =  view;
        }

        return grid;
    }
}