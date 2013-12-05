package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        List<String> moves = new ArrayList<>();
        Position tempPos = new NewPositionCreator(0,1).getNewPosition(srcPos);

        if(isMoveValid(srcPos, tempPos, controller)) {
            moves.add(tempPos.toString());
        }
        return moves.iterator();
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
        int difference = srcPos.getPawnDifference(destPos);
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
            if(difference <= -1 && difference > 0) {
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
            if(difference >= 1 && difference < 0) {
                isValid = true;

            }
        }
        return isValid;
    }

}
