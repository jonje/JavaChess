package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.TeamColor;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 12/11/13
 * Time: 2:36 PM
 */
public class GameRunner implements Runnable {
    private Controller controller;

    public  GameRunner(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void run() {
        controller.setGameRunning(true);
        while(controller.isGameRunning()) {
            if(controller.isKingInCheck()) {
                System.out.println("In Check");
                if(controller.isKingProtectable()) {
                    System.out.println("Is protectable");
                    Iterator<String> protectorMoves = controller.getCurrentPlayer().getProtectorMoves();
                    String moves = "";
                    while(protectorMoves.hasNext()) {
                        moves += "[" + protectorMoves.next() + "]";

                    }
                    System.out.println(moves);
                    controller.getCurrentPlayer().takeTurn();

                } else {
                    controller.setGameRunning(false);
                }
            }
            controller.displayBoaard();
            System.out.println("Press any key to continue...");
            new Scanner(System.in).nextLine();

        }
        String winnerColor = (controller.getCurrentPlayer().getTeamColor().equals(TeamColor.BLACK)) ? TeamColor.WHITE.toString() : TeamColor.BLACK.toString();
        System.out.println("Checkmate! Game Over! \n" + winnerColor + " player is the winner");

    }
}