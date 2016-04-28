package info.richardjones.crossword.app;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import info.richardjones.crossword.app.vo.Cell;

import java.util.List;

public class ClueAdapter extends ArrayAdapter<Cell> {

    private final Context context;
    private final List<Cell> cells;

    public ClueAdapter(Context context, List<Cell> cells) {
        super(context, R.layout.clue_row_layout, R.id.clueList, cells);
        this.context = context;
        this.cells = cells;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.clue_row_layout, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.clueLabel);
        Cell cell = cells.get(position);
        Log.d("ABC", "Cell to string " + cell);
        textView.setText(Html.fromHtml(
            String.format("%s %s %s: %s %s",
                cell.getHighlight() ? "<b>" : "",
                cell.getNumber(),
                cell.getAcrossClue().equals("") ? "Down" : "Across",
                cell.getAcrossClue().equals("") ? cell.getDownClue(): cell.getAcrossClue(),
                cell.getHighlight() ? "<b>" : "")));

        return rowView;
    }

    public int getListPositionOfClue(Integer number) {
        for (Cell cell : cells) {
            cell.setHighlight(false);
        }
        notifyDataSetChanged();

        int res = 0;
        for (int i = 0; i < cells.size(); i++) {
            Cell cell = cells.get(i);
            if (cell.getNumber().equals(number)) {
                cell.setHighlight(true);
                res = i;
            }
        }

        return res;
    }
}

