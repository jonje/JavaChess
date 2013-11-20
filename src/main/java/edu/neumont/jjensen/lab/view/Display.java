package edu.neumont.jjensen.lab.view;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/15/13
 * Time: 2:45 PM
 */
public class Display {
    private Controller controller;
    private boolean cellValuesOn = false;

    public Display() {
        controller = new Controller(this);
    }

    public void displayBoard() {
        for(int j = 1; j <= controller.getBoardSize(); j++) {
            for(char i = controller.getBoardStartingLetter(); i <= controller.getBoardEndingLetter(); i++) {
                String key = "" + i + j;

                Cell cell = controller.getCell(key);

                String emptyChar;

                if(cellValuesOn) {
                    emptyChar = "[ " + cell.getPosition().toString() + "]";
                } else {
                    emptyChar = "[ - ]";

                }

                String output = (cell.isOccupied()) ?  "[ " + cell.getPiece().getAsciiImage() + " ]" :  emptyChar;


                if(i == controller.getBoardEndingLetter()) {
                    output += '\n';
                }
                System.out.print(output);


            }

        }

    }

    public Controller getController() {
        return controller;
    }


}
