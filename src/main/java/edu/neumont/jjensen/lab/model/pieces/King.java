package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:39 PM
 */
public class King extends Piece {
    private final int MAX_MOVE = 1;
    public King() {
        asciiLetter = "k";


    }

    @Override
    public King getInstance() {
        return new King();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos) {
        int differenceInRows = srcPos.getRowDifference(destPos);
        int differenceInColumns = srcPos.getColumnDifference(destPos);
        return (isInBounds(differenceInRows) && isInBounds(differenceInColumns)) ? true : false;
    }

    private boolean isInBounds(int difference) {
        return (difference <= MAX_MOVE && difference >= -MAX_MOVE);
    }
}
