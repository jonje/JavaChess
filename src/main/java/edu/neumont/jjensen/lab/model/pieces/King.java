package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:39 PM
 */
public class King extends Piece {
    public King() {
        asciiLetter = "k";


    }

    @Override
    public King getInstance() {
        return new King();
    }

    @Override
    public boolean isMoveValid(String move) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
