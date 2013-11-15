package edu.neumont.jjensen.lab;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        MoveInterpreter moveInterpreter = new MoveInterpreter(args[0]);
        moveInterpreter.run();
    }

}
