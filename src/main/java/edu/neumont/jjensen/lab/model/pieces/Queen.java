package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:38 PM
 */
public class Queen extends Piece {

    public Queen() {
        asciiLetter = "q";
    }

    @Override
    public Queen getInstance() {
        return new Queen();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position dstPos) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
