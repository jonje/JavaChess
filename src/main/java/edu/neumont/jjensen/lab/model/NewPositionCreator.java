package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 12/4/13
 * Time: 3:27 PM
 */
public class NewPositionCreator {
    private int columnIncreaser;
    private int rowIncreaser;

    public NewPositionCreator(int columnIncreaser, int rowIncreaser) {
        this.columnIncreaser = columnIncreaser;
        this.rowIncreaser = rowIncreaser;

    }

    public Position getNewPosition(Position pos) {
        int columnIndex = pos.getColumnAsIndex() + columnIncreaser;
        int rowIndex = pos.getRowAsIndex() + rowIncreaser;

        return new Position(columnIndex, rowIndex);
    }

}
