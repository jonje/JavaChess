package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.model.pieces.PieceFactoryable;

/**
 * Created with IntelliJ IDEA.
 * User: jjesnen
 * Date: 11/12/13
 * Time: 1:56 PM
 */
public class Piece implements PieceFactoryable{
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


    @Override
    public Piece getInstance() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
