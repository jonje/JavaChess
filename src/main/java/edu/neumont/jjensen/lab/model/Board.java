package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.controller.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:55 PM
 */
public class Board {
    private Cell[][] cells;
    private Controller controller;
    private final int BOARD_SIZE = 8;

    public Board(Controller controller) {
        this.controller = controller;
        setupBoard();

    }


    private void setupBoard() {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                 cells[i][j] = new Cell(controller, new Position(i, j));

            }
        }
    }


    public void setPiece(Position key, Piece piece) {
        cells[key.getColumnAsIndex()][key.getRow()].setPiece(piece);

    }

    public Cell getCell(Position key) {
        if(key.getColumnAsIndex() > cells.length || key.getRowAsIndex() > cells.length) {
            System.out.println(key.getColumn() + " " + key.getRow());
        }
        return cells[key.getColumnAsIndex()][key.getRowAsIndex()];

    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    public void dumpBoard() {
        for(int column = 0; column < 8; column++) {
            for(int row = 0; row < 8; row++) {
                Cell cell = cells[column][row];
                if(cell.isOccupied()) {
                    System.out.println(cell.getPiece());
                }
            }
        }

    }


    public synchronized void update() {
        for(int row = 0; row < 8; row++) {
            for(int column = 0; column < 8; column++) {
                cells[column][row].update();

            }
        }
    }
}
