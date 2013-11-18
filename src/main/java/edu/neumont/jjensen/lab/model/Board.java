package edu.neumont.jjensen.lab.model;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:55 PM
 */
public class Board {
    private HashMap<String, Cell> boardMap;

    private final char STARTING_LETTER = 'A';
    private final char ENDING_LETTER = 'H';

    private final int BOARD_SIZE = 8;

    public Board() {
        setupBoard();

    }


    private void setupBoard() {
        boardMap = new HashMap<>();

        for(char i = STARTING_LETTER; i <= ENDING_LETTER; i++) {
            for(int j = 1; j <= BOARD_SIZE; j++) {
                String key = "" + i + j;
                boardMap.put(key, new Cell(i,j));
            }
        }
    }


    public void setPiece(String key, Piece piece) {
        boardMap.get(key).setPiece(piece);

    }

    public Cell getCell(String key) {
        return boardMap.get(key);

    }

    public char getSTARTING_LETTER() {
        return STARTING_LETTER;
    }

    public char getEndingLetter() {
        return ENDING_LETTER;
    }

    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }




}
