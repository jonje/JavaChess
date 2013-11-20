package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:22 PM
 */
public class Position {
    private char column;
    private int row;

    public Position(char column, int row) {
        this.row = row;
        this.column = column;
    }

    public char getColumn() {
        return column;
    }

    public int getRow(){
        return row;
    }

    @Override
    public String toString() {
        return "" + column + row;
    }

    public int getRowDifference(Position pos2) {
        return this.getRow() - pos2.getRow();
    }

    public int getColumnDifference(Position pos2) {
        return this.getColumn() - pos2.getColumn();
    }
}
