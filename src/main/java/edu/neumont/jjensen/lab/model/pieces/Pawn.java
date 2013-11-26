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
public class Pawn extends Piece {
    private boolean isFirstTurn;
    public Pawn() {
        asciiLetter = "p";
        isFirstTurn = true;
    }

    @Override
    public Pawn getInstance(){
        return new Pawn();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        boolean isValid = true;
        if(((srcPos.isSameColumn(destPos)) && isValidDistance(srcPos, destPos))) {
            Cell cell = controller.getCell(destPos);

            if(cell.isOccupied()) {
                isValid = false;
            }

        } else {
            isValid = false;
        }

        return isValid;

    }

    private boolean isValidDistance(Position srcPos, Position destPos) {
        boolean isValid = false;
        int difference = srcPos.getRowDifference(destPos);

        if(isFirstTurn) {
            if(difference <= 2 && difference >= -2) {
                isValid = true;

            }

        } else {
            if(difference <= 1 && difference >= -1) {
                isValid = true;

            }
        }
        return isValid;

    }

}
