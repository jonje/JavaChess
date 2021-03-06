package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.ChessGame;
import edu.neumont.jjensen.lab.model.NewPositionCreator;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;
import edu.neumont.jjensen.lab.model.Cell;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:41 PM
 */
public class Knight extends Piece {
    private List<NewPositionCreator> knightPositionCreators;

    public Knight() {
        asciiLetter = "n";
        setupKnightPositionCreators();

    }

    public Knight(String whiteImage, String blackImage) {
        super(whiteImage, blackImage);
        setupKnightPositionCreators();
    }

    @Override
    public Knight getInstance() {
        return new Knight(whiteImage, blackImage);
    }

    @Override
    public Iterator<Position> getMovesList(Position srcPos, ChessGame game) {
        List<Position> moves = new ArrayList<>();
        Position tempPos;
        for(NewPositionCreator creator : knightPositionCreators) {
            tempPos = creator.getNewPosition(srcPos);

            if(isInBounds(tempPos, 0, game.getBoardSize())) {
                if(game.getCell(tempPos).isOccupied() && !game.getCell(tempPos).getPiece().getColor().equals(this.getColor())){
                    moves.add(tempPos);
                } else if(!game.getCell(tempPos).isOccupied()) {
                    moves.add(tempPos);
                }

            }


        }

        return moves.iterator();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, ChessGame game) {
        return inBounds(srcPos.getColumnDifference(destPos), srcPos.getRowDifference(destPos)) && isTeamsTurn(game) && !isPieceInWay(destPos, game);

    }

    private boolean isPieceInWay(Position destPos, ChessGame game) {
        Cell cell = game.getCell(destPos);

        return (cell.isOccupied() && cell.getPiece().getColor().equals(this.getColor()));
    }


    private boolean inBounds(int column, int row) {
        return ((column == -2 && row == -1) || (column == 2 && row ==1)) || ((column == -1 && row == -2) || (column == 1 && row == 2));
    }

    private boolean isInBounds(Position pos, int min, int max) {
        return ((pos.getColumnAsIndex() >= min && pos.getColumnAsIndex() < max) && (pos.getRowAsIndex() >= min && pos.getRowAsIndex() < max));
    }

    private void setupKnightPositionCreators() {
        knightPositionCreators = new ArrayList<>();

        knightPositionCreators.add(new NewPositionCreator(1, 2));
        knightPositionCreators.add(new NewPositionCreator(2, 1));
        knightPositionCreators.add(new NewPositionCreator(-1, -2));
        knightPositionCreators.add(new NewPositionCreator(-2, -1));
        knightPositionCreators.add(new NewPositionCreator(1, -2));
        knightPositionCreators.add(new NewPositionCreator(-2, 1));
        knightPositionCreators.add(new NewPositionCreator(-1, 2));
        knightPositionCreators.add(new NewPositionCreator(2, -1));

    }
}
