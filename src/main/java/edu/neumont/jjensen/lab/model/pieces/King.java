package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.Iterator;

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
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        int differenceInRows = srcPos.getRowDifference(destPos);
        int differenceInColumns = srcPos.getColumnDifference(destPos);
        return (isInBounds(differenceInRows) && isInBounds(differenceInColumns) && isTeamsTurn(controller));
    }

    private boolean isInBounds(int difference) {
        return (difference <= MAX_MOVE && difference >= -MAX_MOVE);
    }
}
