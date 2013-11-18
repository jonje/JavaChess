package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 2:03 PM
 */
public class ChessGame {
    private Board board;

    public ChessGame() {
        board = new Board();
    }

    public Cell getCell(String key) {
        return board.getCell(key);
    }

    public int getBoardSize(){
        return board.getBOARD_SIZE();
    }

    public char getBoardEndingLetter(){
        return board.getEndingLetter();
    }

    public char getBoardStartingLetter(){
        return board.getSTARTING_LETTER();
    }


}
