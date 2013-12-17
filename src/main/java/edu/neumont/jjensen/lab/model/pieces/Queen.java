package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.ChessGame;
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

    public Queen(String whiteImage, String blackImage) {
        super(whiteImage, blackImage);
    }

    @Override
    public Queen getInstance() {
        return new Queen(whiteImage, blackImage);
    }

    @Override
    public Iterator<Position> getMovesList(Position srcPos, ChessGame game) {
        List<Position> moves = new ArrayList<>();

        Bishop bish = new Bishop();
        bish.setColor(this.getColor());
        Iterator<Position> iterator = bish.getMovesList(srcPos, game);
        while(iterator.hasNext()) {
            moves.add(iterator.next());
        }
        Rook rook = new Rook();
        rook.setColor(this.getColor());
        iterator = rook.getMovesList(srcPos, game);
        while(iterator.hasNext()) {
            moves.add(iterator.next());
        }

        return moves.iterator();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, ChessGame game) {
        Bishop bish = new Bishop();
        bish.setColor(this.getColor());

        Rook rook = new Rook();
        rook.setColor(this.getColor());
        return (bish.isMoveValid(srcPos, destPos, game)) || (rook.isMoveValid(srcPos, destPos, game));
    }
}
