package edu.neumont.edu.jjensen.lab.model;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
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

    //Todo: Need to finish later
    public void setPiece(String key, Piece piece) {

    }




}
