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
public class Bishop extends Piece {
    public Bishop() {
        asciiLetter = "b";
    }

    @Override
    public Bishop getInstance() {
        return new Bishop();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        boolean isValid = true;
        if((srcPos.getRowDifference(destPos) == srcPos.getColumnDifference(destPos))) {
            Position startingPos = srcPos.getSmallestPosition(destPos);
            Position endingPos = srcPos.getLargestPosition(destPos);
            int rowCounter = startingPos.getRow() + 1;

            for(int i = startingPos.getColumn() + 1;  i < endingPos.getColumn(); i++, rowCounter++) {

                Cell cell = controller.getCell("" + (char)i + rowCounter);

                if(cell.isOccupied() && this.getColor().equals(cell.getPiece().getColor())) {
                    isValid = false;
                    break;
                }
            }

        } else {
            isValid = false;
        }

        return (srcPos.getRowDifference(destPos) == srcPos.getColumnDifference(destPos));
    }
}
