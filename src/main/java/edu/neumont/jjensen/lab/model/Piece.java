package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.controller.Controller;

/**
 * Created with IntelliJ IDEA.
 * User: jjesnen
 * Date: 11/12/13
 * Time: 1:56 PM
 */
public abstract class Piece {
    private Color color;
    private boolean isSelected;
    private String asciiImage;
    protected String asciiLetter;

    public Piece() {

    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
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
        return (color == Color.WHITE) ? character.toUpperCase() : character.toLowerCase();

    }





    public abstract Piece getInstance();

    public abstract boolean isMoveValid(Position srcPos, Position destPos, Controller controller);
}
