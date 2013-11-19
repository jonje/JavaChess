package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:40 PM
 */
public class Pawn extends Piece {
    public Pawn() {
        asciiLetter = "p";
    }

    @Override
    public Pawn getInstance(){
        return new Pawn();
    }

    @Override
    public boolean isMoveValid(String move) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
