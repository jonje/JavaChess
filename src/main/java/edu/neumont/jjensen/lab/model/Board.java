package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:55 PM
 */
public class Board {
    private Cell[][] cells;

    private final int BOARD_SIZE = 8;

    public Board() {
        setupBoard();

    }


    private void setupBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                 cells[i][j] = new Cell();

            }
        }
    }


    public void setPiece(Position key, Piece piece) {
        cells[key.getColumnAsIndex()][key.getRow()].setPiece(piece);

    }

    public Cell getCell(Position key) {
        //System.out.println(key.getColumnAsIndex() + ", " + key.getRowAsIndex());
        return cells[key.getColumnAsIndex()][key.getRowAsIndex()];

    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }




}
