package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:56 PM
 */
public class Cell {
    private Piece piece;
    private Position position;

    private boolean isOccupied;

    public Cell(char column, int row){
        position = new Position(column, row);
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

    public boolean isOccupied() {
        return isOccupied;
    }

    public Position getPosition() {
        return position;
    }

}
