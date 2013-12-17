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
    private List<NewPositionCreator> captureCreators;

    public Pawn() {
        asciiLetter = "p";
        isFirstTurn = true;
    }

    public Pawn(String whiteImage, String blackImage) {
        super(whiteImage, blackImage);
        isFirstTurn = true;
    }
    @Override
    public Pawn getInstance(){
        return new Pawn(whiteImage, blackImage);
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

        for(NewPositionCreator captureCreatror : captureCreators) {
            Position tempPos = captureCreatror.getNewPosition(srcPos);
            if(isInBoundsOfBoard(tempPos) && isCapture(srcPos, tempPos, game)) {
                moves.add(tempPos);

            }
        }


        return moves.iterator();
    }

    private boolean isCapture(Position srcPos, Position destPos, ChessGame game) {
        boolean capture = false;
        if(srcPos.getColumnDifference(destPos) == 1 && srcPos.getRowDifference(destPos) == 1){
            Cell destCell = game.getCell(destPos);
            capture = (destCell.isOccupied() && !destCell.getPiece().getColor().equals(this.getColor()));
        }
        return capture;
    }

    private boolean isInBoundsOfBoard(Position pos) {
        return ((pos.getColumnAsIndex() >= 0 && pos.getColumnAsIndex() < 8) && (pos.getRowAsIndex() >= 0 && pos.getRowAsIndex() < 8));
    }



    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, ChessGame game) {
        boolean isValid = true;
        if(isTeamsTurn(game) && ((srcPos.isSameColumn(destPos)) && isValidDistance(srcPos, destPos))) {

            if(isPieceInWay(destPos, game)) {
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
            if(difference <= 1 && difference > 0) {
                isValid = true;

            }
        }
        return isValid;

    }

    private boolean isPieceInWay(Position destPos, ChessGame game) {
        Cell cell = game.getCell(destPos);
        return (cell.isOccupied());
    }

    private boolean isValidForBlack(int difference) {
        boolean isValid = false;
        if(isFirstTurn) {
            if(difference >= -2 && difference < 0) {
                isValid = true;

            }

        } else {
            if(difference >= -1 && difference < 0) {
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

        captureCreators = new ArrayList<>();
        if(this.getColor().equals(TeamColor.BLACK)) {
            captureCreators.add(new NewPositionCreator(1, 1));
            captureCreators.add(new NewPositionCreator(1, -1));
        } else {
            captureCreators.add(new NewPositionCreator(-1, -1));
            captureCreators.add(new NewPositionCreator(-1,1));
            captureCreators.add(new NewPositionCreator(1, -1));
        }


    }

    public boolean isFirstTurn () {
        return isFirstTurn;
    }

    public void setIsFirstTurn(boolean isFirstTurn) {
        this.isFirstTurn = isFirstTurn;
    }

}
