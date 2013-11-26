package edu.neumont.jjensen.lab.model;

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

    public ChessGame() {
        board = new Board();
        whitePlayer = new Player(TeamColor.WHITE);
        blackPlayer = new Player(TeamColor.BLACK);

        currentPlayer = whitePlayer;
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



}
