package edu.neumont.jjensen.lab.controller;

import edu.neumont.jjensen.lab.model.*;
import edu.neumont.jjensen.lab.view.Display;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/15/13
 * Time: 2:47 PM
 */
public class Controller {
    private ChessGame game;
    private Display display;

    public Controller() {
        game = new ChessGame(this);
        display = new Display(this);
    }

    public int getBoardSize() {
        return game.getBoardSize();
    }

    public Cell getCell(Position key) {
        return game.getCell(key);
    }

    public void setPiece(Position key, Piece piece) {
        game.getCell(key).setPiece(piece);
    }

    public void displayBoaard() {
        display.displayBoard();
    }

    public void endTurn() {
        game.endTurn();
    }

    public Player getCurrentPlayer() {
        return game.getCurrentPlayer();
    }

    public boolean isKingInCheck() {
        return game.isCurrentKingInCheck();
    }

    public ChessGame getGameInstance() {
        return game;
    }


    public boolean isKingProtectable() {
        return game.isKingProtectable();
    }

    public boolean isGameRunning() {
        return game.isGameRunning();
    }

    public void setGameRunning(boolean gameRunning) {
        game.setGameRunning(gameRunning);
    }

    public Display getDisplay() {
        return display;
    }

    public void update() {
        game.update();
    }
}
