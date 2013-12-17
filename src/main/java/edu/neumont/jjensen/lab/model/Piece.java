package edu.neumont.jjensen.lab.model;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jjesnen
 * Date: 11/12/13
 * Time: 1:56 PM
 */
public abstract class Piece {
    private TeamColor color;
    private boolean isSelected;
    private String asciiImage;
    protected String asciiLetter;

    protected String blackImage;
    protected String whiteImage;
    //private ImageIcon image;

    public Piece() {

    }

    public Piece(String whiteImage, String blackImage)  {
        this.whiteImage = whiteImage;
        this.blackImage = blackImage;
    }

    public void setColor(TeamColor color) {
        this.color = color;
    }

    public TeamColor getColor() {
        return color;
    }

    /*public void setImage(ImageIcon image) {
        this.image = image;
    }
*/
    public void setImages(String whiteImage, String blackImage) {
        this.whiteImage = whiteImage;
        this.blackImage = blackImage;
    }

    public String getImage() {

        return (getColor().equals(TeamColor.BLACK)) ? blackImage : whiteImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getAsciiImage() {
        return asciiImage;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setAsciiImage() {
        this.asciiImage = getProperAsciiCase(asciiLetter);
    }

    protected String getProperAsciiCase(String character) {
        return (color == TeamColor.WHITE) ? character.toUpperCase() : character.toLowerCase();

    }

    protected boolean isCurrentTurn(Player currentPlayer) {
        return (this.getColor().equals(currentPlayer.getTeamColor()));
    }

    protected boolean isTeamsTurn(final ChessGame game) {

        return this.getColor().equals(game.getCurrentPlayer().getTeamColor());

    }

    protected void forcePieceMove(Position srcPos, Position destPos, ChessGame game) {
        game.forceMove(srcPos, destPos);

    }

    public abstract Piece getInstance();
    public abstract Iterator<Position> getMovesList(Position srcPos, ChessGame game);

    public abstract boolean isMoveValid(Position srcPos, Position destPos, ChessGame game);
}
