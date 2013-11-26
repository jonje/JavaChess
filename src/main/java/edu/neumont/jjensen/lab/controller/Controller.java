package edu.neumont.jjensen.lab.controller;

import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.ChessGame;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;
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

    public Controller(Display display) {
        game = new ChessGame();
        this.display = display;
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


}
