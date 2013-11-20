package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

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
    public boolean isMoveValid(Position srcPos, Position dstPos) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
