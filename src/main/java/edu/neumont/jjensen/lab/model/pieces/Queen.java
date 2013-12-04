package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.Iterator;

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
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {

        return (new Bishop().isMoveValid(srcPos, destPos, controller)) || (new Rook().isMoveValid(srcPos, destPos, controller));
    }
}
