package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.controller.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:11 PM
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        GameRunner runner = new GameRunner(controller);
        FileInputReader inputReader = new FileInputReader(controller);

        for(String argument: args) {
            inputReader.readInFile(argument);
            controller.displayBoaard();
        }

        runner.run();





    }

}
