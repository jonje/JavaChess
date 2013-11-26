package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:22 PM
 */
public class Position {
    private char column;
    private char row;

    public Position(String key) {

        this.row = key.charAt(1);
        this.column = key.charAt(0);
    }

    public Position(char row, char column) {
        int columnInt = column + 'A';
        int rowInt = row + '1';

        this.column = (char)columnInt;
        this.row = (char)rowInt;

    }


    public char getColumn() {
        return column;
    }

    public int getRow(){
        return row;
    }

    public int getColumnAsIndex() {
        return column - 'A';
    }

    public int getRowAsIndex() {
        return row - '1';
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

    public boolean isSameColumn(Position destPos) {
        return (this.getColumn() == destPos.getColumn());
    }

    public boolean isSameRow(Position destPos) {
        return (this.getRow() == destPos.getRow());
    }

    public Position getSmallestPositionByColumn(Position pos) {
        return (this.getColumn() < pos.getColumn()) ? this : pos;
    }

    public Position getSmallestPositionByRow(Position pos) {
        return (this.getRow() < pos.getRow()) ? this : pos;
    }

    public Position getLargestPositionByColumn(Position pos) {
        return (this.getColumn() > pos.getColumn()) ? this : pos;
    }

    public Position getLargestPositionByRow(Position pos) {
        return (this.getRow() > pos.getRow()) ? this : pos;
    }

    public Position getLargestPosition(Position pos) {
        return (this.getColumn() > pos.getColumn() && this.getRow() > pos.getRow()) ? this : pos;
    }

    public Position getSmallestPosition(Position pos) {
        return (this.getColumn() < pos.getColumn() && this.getRow() < pos.getRow()) ? this : pos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (column != position.column) return false;
        if (row != position.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) column;
        result = 31 * result + row;
        return result;
    }
}
