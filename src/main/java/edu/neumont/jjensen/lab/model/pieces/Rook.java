package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

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
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        boolean isValid = true;
        if((srcPos.isSameColumn(destPos) || srcPos.isSameRow(destPos) && !srcPos.equals(destPos))) {

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
