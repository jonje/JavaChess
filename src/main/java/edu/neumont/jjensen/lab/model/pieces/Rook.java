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
            if(srcPos.isSameColumn(destPos)) {
                Position startingPos = srcPos.getSmallestPositionByRow(destPos);
                Position endingPos = srcPos.getLargestPositionByRow(destPos);

                for(int i = startingPos.getRow() + 1; i < endingPos.getRow(); i++) {
                    Cell cell = controller.getCell("" + startingPos.getColumn() + i);

                    if(cell.isOccupied()) {
                        isValid = false;
                        break;
                    }

                }
            } else {
                Position startingPos = srcPos.getSmallestPositionByColumn(destPos);
                Position endingPos = srcPos.getLargestPositionByColumn(destPos);

                for(int i = startingPos.getColumn() + 1; i < endingPos.getColumn(); i++) {
                    Cell cell = controller.getCell("" + i + startingPos.getRow());

                    if(cell.isOccupied()) {
                        isValid = false;
                        break;
                    }
                }
            }
        } else {
            isValid = false;

        }

        return isValid;

    }


}
