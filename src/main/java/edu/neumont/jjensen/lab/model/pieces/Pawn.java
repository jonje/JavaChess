package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:40 PM
 */
public class Pawn extends Piece {
    private boolean isFirstTurn;
    private List<NewPositionCreator> positionCreators;

    public Pawn() {
        asciiLetter = "p";
        isFirstTurn = true;
    }

    @Override
    public Pawn getInstance(){
        return new Pawn();
    }

    @Override
    public Iterator<Position> getMovesList(Position srcPos, ChessGame game) {
        setupPositionCreators();
        List<Position> moves = new ArrayList<>();
        for(NewPositionCreator positionCreator : positionCreators) {
            Position tempPos = positionCreator.getNewPosition(srcPos);

            if(isMoveValid(srcPos, tempPos, game)) {
                moves.add(tempPos);
            }
        }

        return moves.iterator();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, ChessGame game) {
        boolean isValid = true;
        if(((srcPos.isSameColumn(destPos)) && isValidDistance(srcPos, destPos) && isTeamsTurn(game))) {
            Cell cell = game.getCell(destPos);

            if(cell.isOccupied()) {
                isValid = false;
            }

        } else {
            isValid = false;
        }

        return isValid;

    }

    private boolean isValidDistance(Position srcPos, Position destPos) {
        boolean isValid;
        int difference = srcPos.getPawnDifference(destPos);
        if(this.getColor().equals(TeamColor.WHITE)) {
            isValid = isValidForWhite(difference);

        } else {
            isValid = isValidForBlack(difference);

        }

        return isValid;

    }

    private boolean isValidForWhite(int difference) {
        boolean isValid = false;
        if(isFirstTurn) {
            if(difference <= 2 && difference > 0) {
                isValid = true;

            }

        } else {
            if(difference <= -1 && difference > 0) {
                isValid = true;

            }
        }
        return isValid;

    }

    private boolean isValidForBlack(int difference) {
        boolean isValid = false;
        if(isFirstTurn) {
            if(difference >= -2 && difference < 0) {
                isValid = true;

            }

        } else {
            if(difference >= 1 && difference < 0) {
                isValid = true;

            }
        }
        return isValid;
    }

    private void setupPositionCreators() {
        positionCreators = new ArrayList<>();
        positionCreators.add(new NewPositionCreator(0,1));
        positionCreators.add(new NewPositionCreator(0,-1));

        if(isFirstTurn) {
            positionCreators.add(new NewPositionCreator(0, 2));
            positionCreators.add(new NewPositionCreator(0, -2));
        }
    }

}
