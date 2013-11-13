package edu.neumont.edu.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Cell {
    private Piece piece;
    private Position position;

    private boolean isOccupied;

    public Cell(char row, int column){
        position = new Position(row, column);
    }



    public void setPiece(Piece piece) {
        this.piece = piece;
        setOccupied(true);
    }

    public Piece getPiece() {
        return piece;
    }

    public void removePiece(){
        piece = null;
        setOccupied(false);
    }

    private void setOccupied(boolean isOccupied){
        this.isOccupied = isOccupied;
    }
}
