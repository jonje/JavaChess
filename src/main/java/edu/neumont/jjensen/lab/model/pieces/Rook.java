package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.ChessGame;
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
    public Iterator<Position> getMovesList(Position srcPos, ChessGame game) {
        ArrayList<Position> possibleMoves = new ArrayList<>();
        possibleMoves.addAll(getColumnMoves(srcPos, game));
        possibleMoves.addAll(getRowMoves(srcPos, game));

        return possibleMoves.iterator();
    }

    private ArrayList<Position> getColumnMoves(Position srcPos, ChessGame game) {
        boolean isPieceFound = false;
        ArrayList<Position> columnMoves = new ArrayList<>();


        for(int i = srcPos.getColumnAsIndex() + 1; i < game.getBoardSize() && !isPieceFound; i++) {
            Position tempPos = new Position(i, srcPos.getRowAsIndex());
            if(isInBoardBounds(tempPos, game)) {
                Cell cell = game.getCell(tempPos);

                    isPieceFound = cell.isOccupied();
                    columnMoves.add(tempPos);

            }

        }

        for(int i = srcPos.getColumnAsIndex() - 1; i >= 0 && !isPieceFound; i--) {
            Position tempPos = new Position(i, srcPos.getRowAsIndex());
            if(isInBoardBounds(tempPos, game)) {
                Cell cell = game.getCell(tempPos);
                    isPieceFound = cell.isOccupied();
                    columnMoves.add(tempPos);

            }


        }

        return columnMoves;

    }

    private boolean isInBoardBounds(Position srcPos, ChessGame game) {
        return ((srcPos.getColumnAsIndex() >= 0 && srcPos.getColumnAsIndex() < game.getBoardSize()) && (srcPos.getRowAsIndex() >= 0 && srcPos.getRowAsIndex() < game.getBoardSize()));
    }

    private ArrayList<Position> getRowMoves(Position srcPos, ChessGame game) {
        ArrayList<Position> rowMoves = new ArrayList<>();
        boolean isPieceFound = false;

        for(int i = srcPos.getRowAsIndex() + 1; i < game.getBoardSize() && !isPieceFound; i++) {
            Position tempPos = new Position(srcPos.getColumnAsIndex(), i);
            Cell cell = game.getCell(tempPos);

                isPieceFound = cell.isOccupied();
                rowMoves.add(tempPos);

        }

        isPieceFound = false;
        for(int i = srcPos.getRowAsIndex() - 1; i >= 0 && !isPieceFound; i--) {
            Position tempPos = new Position(srcPos.getColumnAsIndex(), i);
            Cell cell = game.getCell(tempPos);

            isPieceFound = cell.isOccupied();
            rowMoves.add(tempPos);
        }

        return rowMoves;
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, ChessGame game) {
        boolean isValid;
        if((srcPos.isSameColumn(destPos) || srcPos.isSameRow(destPos) && !srcPos.equals(destPos) && isTeamsTurn(game))) {

            if(srcPos.isSameColumn(destPos)){
               isValid = isPieceInRowPath(srcPos, destPos, game);
            } else {
                isValid = isPieceInColumnPath(srcPos, destPos, game);
            }
        } else {
            isValid = false;

        }

        return isValid;

    }



    private boolean isPieceInRowPath(Position srcPos, Position destPos, ChessGame game) {
        boolean isValid = true;
        boolean isPieceFound = false;
        Position startingPos = srcPos.getSmallestPositionByRow(destPos);
        Position endingPos = srcPos.getLargestPositionByRow(destPos);

        for(int i = startingPos.getRow() + 1; i < endingPos.getRow() && !isPieceFound; i++) {
            Cell cell = game.getCell(new Position("" + startingPos.getColumn() + (char)i));

            if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                isValid = false;
                isPieceFound = true;

            }

        }

        return isValid;

    }

    private boolean isPieceInColumnPath(Position srcPos, Position destPos, ChessGame game) {
        boolean isValid = true;
        boolean isPieceFound = false;

        Position startingPos = srcPos.getSmallestPositionByColumn(destPos);
        Position endingPos = srcPos.getLargestPositionByColumn(destPos);

        for(int i = startingPos.getColumn() + 1; i < endingPos.getColumn() && !isPieceFound; i++) {
            Position pos = new Position((char)i, startingPos.getRow());
            Cell cell = game.getCell(pos);

            if(cell.isOccupied()  && this.getColor().equals(cell.getPiece().getColor())) {
                isValid = false;
                isPieceFound = true;
            }
        }

        return isValid;
    }


}
