package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 2:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Position {
    private char row;
    private int column;

    public Position(char row, int column) {
        this.row = row;
        this.column = column;
    }

    public char getRow() {
        return row;
    }

    public int getColumn(){
        return column;
    }

    @Override
    public String toString() {
        return "" + row + column;
    }
}
