package edu.neumont.jjensen.lab.controller;

import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.ChessGame;
import edu.neumont.jjensen.lab.model.Piece;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/15/13
 * Time: 2:47 PM
 */
public class Controller {
    private ChessGame game;

    public Controller() {
        game = new ChessGame();
    }

    public char getBoardStartingLetter() {
        return game.getBoardStartingLetter();
    }

    public char getBoardEndingLetter() {
        return game.getBoardEndingLetter();

    }

    public int getBoardSize() {
        return game.getBoardSize();
    }

    public Cell getCell(String key) {
        return game.getCell(key);
    }

    public void setPiece(String key, Piece piece) {
        game.getCell(key.toUpperCase()).setPiece(piece);
    }


}
