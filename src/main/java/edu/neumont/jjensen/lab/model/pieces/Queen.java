package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        List<String> moves = new ArrayList<>();

        Iterator<String> iterator = new Bishop().getMovesList(srcPos, controller);
        while(iterator.hasNext()) {
            moves.add(iterator.next());
        }

        iterator = new Rook().getMovesList(srcPos, controller);
        while(iterator.hasNext()) {
            moves.add(iterator.next());
        }

        return moves.iterator();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {

        return (new Bishop().isMoveValid(srcPos, destPos, controller)) || (new Rook().isMoveValid(srcPos, destPos, controller));
    }
}
