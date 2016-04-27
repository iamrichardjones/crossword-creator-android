package info.richardjones.crossword.app.vo;


import android.graphics.Color;

import java.util.Comparator;

//"[number:1;letter:C;downClue:AB;acrossClue:CD][letter:A][letter:T][]|" +
//"[letter:O}][][][number:2;letter:B;down-clue:TO...]|" +
//"[number:3;letter:T; acrossClue:big bush][letter:R][letter:E][letter:E]";
public class Cell {

    public static final Comparator<Cell> SORT_BY_NUMBER = new Comparator<Cell>() {
        @Override
        public int compare(Cell lhs, Cell rhs) {
            return lhs.number.compareTo(rhs.number);
        }
    };

    private Integer number;
    private String letter;
    private String acrossClue;
    private String downClue;
    private int backgroundColour;

    public Cell(){
        this(Color.WHITE, -1, "", "", "");
    };

    public Cell(int backgroundColour, Integer number, String letter, String acrossClue, String downClue) {
        this.backgroundColour = backgroundColour;
        this.number = number;
        this.letter = letter;
        this.acrossClue = acrossClue;
        this.downClue = downClue;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public void setAcrossClue(String acrossClue) {
        this.acrossClue = acrossClue;
    }

    public void setDownClue(String downClue) {
        this.downClue = downClue;
    }

    public void setBackgroundColour(int backgroundColour) {
        this.backgroundColour = backgroundColour;
    }

    public int getBackgroundColour() {
        return backgroundColour;
    }

    public Integer getNumber() {
        return number;
    }

    public String getLetter() {
        return letter;
    }

    public String getAcrossClue() {
        return acrossClue.replaceAll("\\+", " ");
    }

    public String getDownClue() {
        return downClue;
    }

    public static Cell newBlankCell() {
        return new Cell(Color.BLACK, -1, "", "", "");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Colour: ").append(backgroundColour).append(", ");
        builder.append("Letter: ").append(letter).append(", ");;
        builder.append("Number: ").append(number).append(", ");;
        builder.append("AcrossClue: ").append(acrossClue).append(", ");;
        builder.append("DownClue: ").append(downClue);

        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cell)) {
            return false;
        }
        Cell that = Cell.class.cast(o);
        return this.backgroundColour == that.backgroundColour &&
               this.letter.equals(that.letter) &&
               this.number.equals(that.number) &&
               this.acrossClue.equals(that.acrossClue) &&
               this.downClue.equals(that.downClue);
    }

    @Override
    public int hashCode() {
        return letter.hashCode();
    }
}

