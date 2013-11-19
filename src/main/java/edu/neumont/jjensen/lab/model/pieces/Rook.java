package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:40 PM
 */
public class Rook extends Piece {
    public Rook() {
        asciiLetter = "r";

    }

    @Override
    public Rook getInstance(){
        return new Rook();
    }



}
