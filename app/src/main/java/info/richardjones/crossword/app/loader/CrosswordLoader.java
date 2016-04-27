package info.richardjones.crossword.app.loader;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.gson.Gson;
import info.richardjones.crossword.app.vo.Cell;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class CrosswordLoader {

    private static final Character ROW_SEPARATOR = '|';
    private static final Character CELL_SEPARATOR = '-';

    private String str;
    private Integer height;
    private Integer width;

    public CrosswordLoader(String str) {
        this.str = str;
        height = getCountInString(str, ROW_SEPARATOR) + 1;
        width = getCountInString(str.substring(0, str.indexOf(ROW_SEPARATOR)), CELL_SEPARATOR) + 1;
    }

    // "{number:1,letter:C,downClue:Bed}-{letter:A}-{letter:T}-{blank}|" +
    // "{letter:O}-{blank}-{blank}-{number:2,letter:B,downClue:To...}|" +
    // "{number:3,letter:T,acrossClue:big+bush}-{letter:R}-{letter:E}-{letter:E}";
    private Integer getCountInString(String str, Character toFind) {
        return CharMatcher.is(toFind).countIn(str);
    }

    public List<Cell> getCells() {

        List<Cell> cellsList = newArrayList();
        Gson gson = new Gson();

        List<String> rows = newArrayList(Splitter.on(ROW_SEPARATOR).split(str));

        for (String row : rows) {
            List<String> cells = newArrayList(Splitter.on(CELL_SEPARATOR).omitEmptyStrings().split(row));
            for (String cell: cells) {
                if (cell.equals("{blank}")) {
                    cellsList.add(Cell.newBlankCell());
                }
                else {
                    cellsList.add(gson.fromJson(cell, Cell.class));
                }
            }
        }
        //now parse the json
        return cellsList;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWidth() {
        return width;
    }
}
