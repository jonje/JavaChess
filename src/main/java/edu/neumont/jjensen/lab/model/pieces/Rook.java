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
public class Rook extends Piece {
    public Rook() {
        asciiLetter = "r";

    }

    @Override
    public Rook getInstance(){
        return new Rook();
    }

    @Override
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        ArrayList<String> possibleMoves = new ArrayList<>();
        possibleMoves.addAll(getColumnMoves(srcPos, controller));
        possibleMoves.addAll(getRowMoves(srcPos, controller));

        return possibleMoves.iterator();
    }

    private ArrayList<String> getColumnMoves(Position srcPos, Controller controller) {
        boolean isPieceFound = false;
        ArrayList<String> columnMoves = new ArrayList<>();


        for(int i = srcPos.getColumnAsIndex() + 1; i < controller.getBoardSize() && !isPieceFound; i++) {
            Position tempPos = new Position(i, srcPos.getRowAsIndex());
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied()  && this.getColor().equals(cell.getPiece().getColor())) {
                isPieceFound = true;

            } else {
                columnMoves.add(tempPos.toString());
            }


        }

        for(int i = srcPos.getColumnAsIndex() - 1; i >= 0 && !isPieceFound; i--) {
            Position tempPos = new Position(i, srcPos.getRowAsIndex());
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied()  && this.getColor().equals(cell.getPiece().getColor())) {
                isPieceFound = true;

            } else {
                columnMoves.add(tempPos.toString());
            }


        }

        return columnMoves;

    }

    private ArrayList<String> getRowMoves(Position srcPos, Controller controller) {
        ArrayList<String> rowMoves = new ArrayList<>();
        boolean isPieceFound = false;

        for(int i = srcPos.getRowAsIndex() + 1; i < controller.getBoardSize() && !isPieceFound; i++) {
            Position tempPos = new Position(srcPos.getColumnAsIndex(), i);
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {

                isPieceFound = true;

            } else {
                rowMoves.add(tempPos.toString());
            }


        }

        isPieceFound = false;
        for(int i = srcPos.getRowAsIndex() - 1; i >= 0 && !isPieceFound; i--) {
            Position tempPos = new Position(srcPos.getColumnAsIndex(), i);
            Cell cell = controller.getCell(tempPos);

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {

                isPieceFound = true;

            } else {
                rowMoves.add(tempPos.toString());
            }


        }

        return rowMoves;
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        boolean isValid;
        if((srcPos.isSameColumn(destPos) || srcPos.isSameRow(destPos) && !srcPos.equals(destPos) && isTeamsTurn(controller))) {

            if(srcPos.isSameColumn(destPos)){
               isValid = isPieceInRowPath(srcPos, destPos, controller);
            } else {
                isValid = isPieceInColumnPath(srcPos, destPos, controller);
            }
        } else {
            isValid = false;

        }

        return isValid;

    }



    private boolean isPieceInRowPath(Position srcPos, Position destPos, Controller controller) {
        boolean isValid = true;
        boolean isPieceFound = false;
        Position startingPos = srcPos.getSmallestPositionByRow(destPos);
        Position endingPos = srcPos.getLargestPositionByRow(destPos);

        for(int i = startingPos.getRow() + 1; i < endingPos.getRow() && !isPieceFound; i++) {
            Cell cell = controller.getCell(new Position("" + startingPos.getColumn() + (char)i));

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isValid = false;
                isPieceFound = true;

            }

        }

        return isValid;

    }

    private boolean isPieceInColumnPath(Position srcPos, Position destPos, Controller controller) {
        boolean isValid = true;
        boolean isPieceFound = false;

        Position startingPos = srcPos.getSmallestPositionByColumn(destPos);
        Position endingPos = srcPos.getLargestPositionByColumn(destPos);

        for(int i = startingPos.getColumn() + 1; i < endingPos.getColumn() && !isPieceFound; i++) {
            Position pos = new Position((char)i, startingPos.getRow());
            Cell cell = controller.getCell(pos);

            if(cell.isOccupied()  && this.getColor().equals(cell.getPiece().getColor())) {
                isValid = false;
                isPieceFound = true;
            }
        }

        return isValid;
    }


}
