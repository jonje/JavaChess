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
