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
    public Iterator<Position> getMovesList(Position srcPos, Controller controller) {
        ArrayList<Position> moves = new ArrayList<>();
        moves.addAll(getForwardMoves(srcPos, controller));
        moves.addAll(getBackwardMoves(srcPos, controller));
        moves.addAll(getOppositeDirection(srcPos, controller));
        return moves.iterator();
    }

    private ArrayList<Position> getForwardMoves(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<Position> forwardMoves = new ArrayList<>();

        int rowCounter = srcPos.getRowAsIndex() + 1;

        for(int i = srcPos.getColumnAsIndex() + 1;  i < controller.getBoardSize() && rowCounter < controller.getBoardSize() && !isPieceFound; i++, rowCounter++) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            isPieceFound = cell.isOccupied();
            forwardMoves.add(tempPos);

        }

        return forwardMoves;
    }

    private ArrayList<Position> getBackwardMoves(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<Position> backwardMoves = new ArrayList<>();

        int rowCounter = srcPos.getRowAsIndex() - 1;

        for(int i = srcPos.getColumnAsIndex() - 1;  i >= 0 && rowCounter >= 0 && !isPieceFound; i--, rowCounter--) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            isPieceFound = cell.isOccupied();
            backwardMoves.add(tempPos);


        }

        return backwardMoves;
    }

    private ArrayList<Position> getOppositeDirection(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<Position> moves = new ArrayList<>();

        int rowCounter = srcPos.getRowAsIndex() - 1;

        for(int i = srcPos.getColumnAsIndex() + 1;  i < controller.getBoardSize() && rowCounter >= 0 && !isPieceFound; i++, rowCounter--) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            isPieceFound = cell.isOccupied();
            moves.add(tempPos);

        }

        rowCounter = srcPos.getRowAsIndex() + 1;
        isPieceFound = false;

        for(int i = srcPos.getColumnAsIndex() - 1;  i >= 0 && rowCounter < controller.getBoardSize() && !isPieceFound; i--, rowCounter++) {
            Position tempPos = new Position(i, rowCounter);
            Cell cell = controller.getCell(tempPos);

            isPieceFound = cell.isOccupied();
            moves.add(tempPos);

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
