package edu.neumont.jjensen.lab.view;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.Position;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/15/13
 * Time: 2:45 PM
 */
public class Display {
    private Controller controller;
    private final String PADDING = "\n";

    public Display(Controller controller) {
        this.controller = controller;

    }

    public void displayBoard() {
        for(int row = 0; row < controller.getBoardSize(); row++) {
            String output = "";
            for(int column =0; column < controller.getBoardSize(); column++) {

                Cell cell = controller.getCell(new Position(column, row));

                String emptyCell = "[ - ]";

                 output += (cell.isOccupied()) ?  "[ " + cell.getPiece().getAsciiImage() + " ]" :  emptyCell;


            }
            System.out.println(output);

        }

        System.out.println(PADDING);

    }

    public Controller getController() {
        return controller;
    }


}
