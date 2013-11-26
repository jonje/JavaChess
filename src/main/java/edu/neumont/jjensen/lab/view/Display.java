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
    private boolean cellValuesOn = true;

    public Display() {
        controller = new Controller(this);
    }

    public void displayBoard() {
        for(int j = 0; j < controller.getBoardSize(); j++) {
            String output = "";
            for(int i =0; i < controller.getBoardSize(); i++) {

                Cell cell = controller.getCell(new Position((char)j, (char)i));

                String emptyCell =  "[ - ]";

                 output += (cell.isOccupied()) ?  "[ " + cell.getPiece().getAsciiImage() + " ]" :  emptyCell;


//                if(i == controller.getBoardSize() -1) {
//                    output += '\n';
//                }

            }
            System.out.println(output);

        }

    }

    public Controller getController() {
        return controller;
    }


}
