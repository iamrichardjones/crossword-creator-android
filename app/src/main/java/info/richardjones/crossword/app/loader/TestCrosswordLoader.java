package info.richardjones.crossword.app.loader;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.gson.Gson;
import info.richardjones.crossword.app.vo.Cell;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class TestCrosswordLoader {

    private CrosswordLoader crosswordLoader;

    public TestCrosswordLoader() {
        crosswordLoader = new CrosswordLoader(getTestString());
    }

    /*
    * [number:1;letter:C;down-clue:AB;across-clue:CD][letter:A][letter:T][]|
    * [letter:O}][][][number:2;letter:B;down-clue:TO...]|
    * [number:3;letter:T; across-clue:big bush][letter:R][letter:E][letter:E]
    */


    public List<Cell> getCells() {
        return crosswordLoader.getCells();
    }

    public String getTestString() {
        return
                /* 4 * 3*/
                "{number:1,letter:C,downClue:Bed, acrossClue:random}-{letter:A}-{letter:T}-{blank}|" +
                "{letter:O}-{blank}-{blank}-{number:2,letter:B,downClue:To...}|" +
                "{number:3,letter:T,acrossClue:big+bush}-{letter:R}-{letter:E}-{letter:E}";

//        /* 2 * 3*/
//        "{number:1,letter:C,downClue:Bed}-{letter:A}|" +
//                "{letter:O}-{blank}|" +
//                "{number:3,letter:T,acrossClue:big+bush}-{letter:R}";

//        /* 5 * 2*/
//        "{number:1,letter:C,downClue:Bed}-{letter:A}-{letter:T}-{blank}-{number:3,letter:B,downClue:Digging+Stick}|" +
//                "{letter:O}-{blank}-{blank}-{number:2,letter:B,downClue:To...}-{letter:A}";
    }

    public Integer getHeight(){
        return crosswordLoader.getHeight();
    }

    public Integer getWidth(){
        return crosswordLoader.getWidth();
    }
}
