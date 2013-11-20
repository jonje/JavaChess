package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:40 PM
 */
public class Bishop extends Piece {
    public Bishop() {
        asciiLetter = "b";
    }

    @Override
    public Bishop getInstance() {
        return new Bishop();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position dstPos) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
