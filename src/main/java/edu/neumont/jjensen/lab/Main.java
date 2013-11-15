package edu.neumont.jjensen.lab;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:11 PM
 */
public class Main {
    public static void main(String[] args) {

        MoveInterpreter moveInterpreter = new MoveInterpreter(args[0]);
        moveInterpreter.run();
    }

}
