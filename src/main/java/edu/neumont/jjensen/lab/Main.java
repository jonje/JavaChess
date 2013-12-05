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
        FileInputReader inputReader = new FileInputReader(display.getController());
        for(String argument: args) {
            inputReader.readInFile(argument);

        }
        display.displayBoard();



    }

}
