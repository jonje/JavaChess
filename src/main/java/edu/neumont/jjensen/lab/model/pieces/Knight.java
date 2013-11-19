package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

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
}
