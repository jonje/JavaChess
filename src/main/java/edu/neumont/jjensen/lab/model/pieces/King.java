package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.ChessGame;
import edu.neumont.jjensen.lab.model.NewPositionCreator;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:39 PM
 */
public class King extends Piece {
    private final int MAX_MOVE = 1;
    private List<NewPositionCreator> positionCreators;
    public King() {
        asciiLetter = "k";
        setupPositionCreators();


    }

    @Override
    public King getInstance() {
        return new King();
    }

    @Override
    public Iterator<Position> getMovesList(Position srcPos, ChessGame game) {
        List<Position> moves = new ArrayList<>();

        for(NewPositionCreator positionCreator : positionCreators) {
            Position tempPos = positionCreator.getNewPosition(srcPos);

            if(isInBoardBounds(tempPos, game) && !game.getCell(tempPos).isOccupied()) {
                moves.add(tempPos);

            }
        }
        return moves.iterator();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, ChessGame game) {
        int differenceInRows = srcPos.getRowDifference(destPos);
        int differenceInColumns = srcPos.getColumnDifference(destPos);
        return (isInBounds(differenceInRows) && isInBounds(differenceInColumns) && isTeamsTurn(game));
    }

    private boolean isInBounds(int difference) {
        return (difference <= MAX_MOVE && difference >= -MAX_MOVE);
    }

    private boolean isInBoardBounds(Position srcPos, ChessGame game) {
        return ((srcPos.getColumnAsIndex() >= 0 && srcPos.getColumnAsIndex() < game.getBoardSize()) && (srcPos.getRowAsIndex() >= 0 && srcPos.getRowAsIndex() < game.getBoardSize()));
    }

    private void setupPositionCreators() {
        positionCreators = new ArrayList<>();
        positionCreators.add(new NewPositionCreator(1, 0));
        positionCreators.add(new NewPositionCreator(0, 1));
        positionCreators.add(new NewPositionCreator(-1, 0));
        positionCreators.add(new NewPositionCreator(0, -1));
        positionCreators.add(new NewPositionCreator(1, 1));
        positionCreators.add(new NewPositionCreator(1, -1));
        positionCreators.add(new NewPositionCreator(-1, 1));
        positionCreators.add(new NewPositionCreator(-1, -1));
    }
}
