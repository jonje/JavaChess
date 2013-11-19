package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:39 PM
 */
public class King extends Piece implements PieceFactoryable{
    public King() {
        asciiLetter = "k";


    }

    @Override
    public King getInstance() {
        return new King();
    }
}
