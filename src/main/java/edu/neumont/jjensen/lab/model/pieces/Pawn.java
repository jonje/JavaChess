package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;
import edu.neumont.jjensen.lab.model.TeamColor;

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
        if(((srcPos.isSameColumn(destPos)) && isValidDistance(srcPos, destPos) && isTeamsTurn(controller))) {
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
        boolean isValid;
        int difference = srcPos.getRowDifference(destPos);
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

}
