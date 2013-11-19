package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

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
}
