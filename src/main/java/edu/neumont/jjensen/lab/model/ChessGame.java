package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.MoveInterpreter;
import edu.neumont.jjensen.lab.controller.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:03 PM
 */
public class ChessGame {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private Player currentPlayer;
    private MoveInterpreter moveInterpreter;
    private Controller controller;
    private boolean gameRunning;

    public ChessGame(Controller controller) {
        board = new Board();
        this.controller = controller;
        whitePlayer = new Player(TeamColor.WHITE, this);
        blackPlayer = new Player(TeamColor.BLACK, this);

        currentPlayer = whitePlayer;
        moveInterpreter = new MoveInterpreter(controller);
    }

    public Cell getCell(Position key) {
        return board.getCell(key);
    }

    public int getBoardSize(){
        return board.getBOARD_SIZE();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void endTurn() {
        currentPlayer = (currentPlayer.getTeamColor().equals(TeamColor.WHITE)) ? blackPlayer : whitePlayer;

    }

    public boolean isCurrentKingInCheck() {
        return currentPlayer.isKingInCheck();
    }

    public void performMove(String move) {
        moveInterpreter.interpretMove(move);
    }


    public boolean isKingProtectable() {
        return currentPlayer.isKingProtectable();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
