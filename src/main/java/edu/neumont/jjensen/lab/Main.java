package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.view.Display;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:11 PM
 */
public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        MoveInterpreter moveInterpreter = new MoveInterpreter(args[0], display.getController());
        moveInterpreter.run();

        display.displayBoard();
    }

}
