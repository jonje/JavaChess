package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:40 PM
 */
public class Bishop extends Piece {
    public Bishop() {
        asciiLetter = "b";
    }

    @Override
    public Bishop getInstance() {
        return new Bishop();
    }

    @Override
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        ArrayList<String> moves = new ArrayList<>();
        moves.addAll(getForwardMoves(srcPos, controller));
        moves.addAll(getBackwardMoves(srcPos, controller));
        moves.addAll(getOppositeDirection(srcPos, controller));
        return moves.iterator();
    }

    private ArrayList<String> getForwardMoves(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<String> forwardMoves = new ArrayList<>();

        int rowCounter = srcPos.getRowAsIndex() + 1;

        for(int i = srcPos.getColumnAsIndex() + 1;  i < controller.getBoardSize() && rowCounter < controller.getBoardSize() && !isPieceFound; i++, rowCounter++) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isPieceFound = true;

            } else {
                forwardMoves.add(tempPos.toString());
            }

        }

        return forwardMoves;
    }

    private ArrayList<String> getBackwardMoves(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<String> backwardMoves = new ArrayList<>();

        int rowCounter = srcPos.getRowAsIndex() - 1;

        for(int i = srcPos.getColumnAsIndex() - 1;  i >= 0 && rowCounter >= 0 && !isPieceFound; i--, rowCounter--) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isPieceFound = true;

            } else {
                backwardMoves.add(tempPos.toString());
            }

        }

        return backwardMoves;
    }

    private ArrayList<String> getOppositeDirection(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<String> moves = new ArrayList<>();

        int rowCounter = srcPos.getRowAsIndex() - 1;

        for(int i = srcPos.getColumnAsIndex() + 1;  i < controller.getBoardSize() && rowCounter >= 0 && !isPieceFound; i++, rowCounter--) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isPieceFound = true;

            } else {
                moves.add(tempPos.toString());
            }

        }

        rowCounter = srcPos.getRowAsIndex() + 1;
        isPieceFound = false;

        for(int i = srcPos.getColumnAsIndex() - 1;  i >= 0 && rowCounter < controller.getBoardSize() && !isPieceFound; i--, rowCounter++) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isPieceFound = true;

            } else {
                moves.add(tempPos.toString());
            }

        }

       return moves;
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        boolean isValid;
        if((srcPos.getRowDifference(destPos) == srcPos.getColumnDifference(destPos)) && isTeamsTurn(controller)) {
            isValid = isPieceNotInPath(srcPos, destPos, controller);

        } else {
            isValid = false;
        }

        return isValid; //(srcPos.getRowDifference(destPos) == srcPos.getColumnDifference(destPos));
    }

    private boolean isPieceNotInPath(Position srcPos, Position destPos, Controller controller) {
        boolean isValid = true;
        boolean isPieceFound = false;

        Position startingPos = srcPos.getSmallestPosition(destPos);
        Position endingPos = srcPos.getLargestPosition(destPos);
        int rowCounter = startingPos.getRow() + 1;

        for(int i = startingPos.getColumn() + 1;  i < endingPos.getColumn() && !isPieceFound; i++, rowCounter++) {

            Cell cell = controller.getCell(new Position("" + (char)i + rowCounter));

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isValid = false;
                isPieceFound = true;

            }
        }

        return isValid;
    }
}
