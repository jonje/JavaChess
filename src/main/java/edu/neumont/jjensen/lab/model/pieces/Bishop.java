package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

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
    public boolean isMoveValid(String move) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
