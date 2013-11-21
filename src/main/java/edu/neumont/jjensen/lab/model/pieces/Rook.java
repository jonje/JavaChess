package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

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

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos) {
        return (!columnsMatch(srcPos.getColumn(), destPos.getColumn()) && !rowsMatch(srcPos.getRow(), destPos.getRow())) ? false : true;

    }


}
