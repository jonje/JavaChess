package edu.neumont.jjensen.lab.model.pieces;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.NewKnightPositionCreator;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:41 PM
 */
public class Knight extends Piece {
    private List<NewKnightPositionCreator> knightPositionCreators;

    public Knight() {
        asciiLetter = "n";
        setupKnightPositionCreators();

    }

    @Override
    public Knight getInstance() {
        return new Knight();
    }

    @Override
    public Iterator<String> getMovesList(Position srcPos, Controller controller) {
        List<String> moves = new ArrayList<>();
        Position tempPos;
        for(NewKnightPositionCreator creator : knightPositionCreators) {
            tempPos = creator.getNewPosition(srcPos);

            if(isInBounds(tempPos, 0, controller.getBoardSize())) {
                moves.add(tempPos.toString());
            }


        }

        return moves.iterator();
    }

    @Override
    public boolean isMoveValid(Position srcPos, Position destPos, Controller controller) {
        return inBounds(srcPos.getColumnDifference(destPos), srcPos.getRowDifference(destPos)) && isTeamsTurn(controller);

    }


    private boolean inBounds(int column, int row) {
        return ((column == -2 && row == -1) || (column == 2 && row ==1)) || ((column == -1 && row == -2) || (column == 1 && row == 2));
    }

    private boolean isInBounds(Position pos, int min, int max) {
        return ((pos.getColumnAsIndex() >= min && pos.getColumnAsIndex() < max) && (pos.getRowAsIndex() >= min && pos.getRowAsIndex() < max));
    }

    private void setupKnightPositionCreators() {
        knightPositionCreators = new ArrayList<>();

        knightPositionCreators.add(new NewKnightPositionCreator(1, 2));
        knightPositionCreators.add(new NewKnightPositionCreator(2, 1));
        knightPositionCreators.add(new NewKnightPositionCreator(-1, -2));
        knightPositionCreators.add(new NewKnightPositionCreator(-2, -1));
        knightPositionCreators.add(new NewKnightPositionCreator(1, -2));
        knightPositionCreators.add(new NewKnightPositionCreator(-2, 1));
        knightPositionCreators.add(new NewKnightPositionCreator(-1, 2));
        knightPositionCreators.add(new NewKnightPositionCreator(2, -1));

    }
}
