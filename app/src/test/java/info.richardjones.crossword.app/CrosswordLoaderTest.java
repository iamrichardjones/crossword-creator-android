package info.richardjones.crossword.app;

import static org.junit.Assert.assertEquals;

import android.graphics.Color;
import info.richardjones.crossword.app.loader.TestCrosswordLoader;
import info.richardjones.crossword.app.vo.Cell;
import org.junit.Test;

import java.util.List;


public class CrosswordLoaderTest {



    // "{number:1,letter:C,downClue:Bed}-{letter:A}-{letter:T}-{blank}|" +
    // "{letter:O}-{blank}-{blank}-{number:2,letter:B,downClue:To...}|" +
    // "{number:3,letter:T,acrossClue:big+bush}-{letter:R}-{letter:E}-{letter:E}";
    @Test
    public void testTestCrosswordLoader() {

        TestCrosswordLoader loader = new TestCrosswordLoader();
        assertEquals(new Integer(3), loader.getHeight());
        assertEquals(new Integer(4), loader.getWidth());

        List<Cell> cells = loader.getCells();
        assertEquals(12, cells.size());
        assertEquals(new Cell(Color.WHITE, 1, "C", "", "Bed"), cells.get(0));
        assertEquals(new Cell(Color.WHITE, -1, "A", "", ""), cells.get(1));
        assertEquals(new Cell(Color.WHITE, -1, "T", "", ""), cells.get(2));
        assertEquals(Cell.newBlankCell(), cells.get(3));
        assertEquals(new Cell(Color.WHITE, -1, "O", "", ""), cells.get(4));
        assertEquals(Cell.newBlankCell(), cells.get(5));
        assertEquals(Cell.newBlankCell(), cells.get(6));
        assertEquals(new Cell(Color.WHITE, 2, "B", "", "To..."), cells.get(7));
        assertEquals(new Cell(Color.WHITE, 3, "T", "big+bush", ""), cells.get(8));
        assertEquals(new Cell(Color.WHITE, -1, "R", "", ""), cells.get(9));
        assertEquals(new Cell(Color.WHITE, -1, "E", "", ""), cells.get(10));
        assertEquals(new Cell(Color.WHITE, -1, "E", "", ""), cells.get(11));
    }
}