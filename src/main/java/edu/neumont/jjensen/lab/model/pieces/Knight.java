package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:41 PM
 */
public class Knight extends Piece {
    public Knight() {
        asciiLetter = "n";

    }

    @Override
    public Knight getInstance() {
        return new Knight();
    }

    @Override
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        return inBounds(srcPos.getColumnDifference(destPos), srcPos.getRowDifference(destPos)) && isTeamsTurn(controller);

    }


    private boolean inBounds(int column, int row) {
        return ((column == -2 && row == -1) || (column == 2 && row ==1)) || ((column == -1 && row == -2) || (column == 1 && row == 2));
    }
}
