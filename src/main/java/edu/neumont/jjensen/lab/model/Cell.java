package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.controller.Controller;

import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:56 PM
 */
public class Cell extends JPanel implements MouseListener{
    private Piece piece;
    private Position position;
    private JLabel imageLabel;
    private Controller controller;
    private boolean isOccupied;
    private Color color;

    public Cell(Controller controller, Position position) {
        this.position = position;
        this.controller = controller;
        this.setSize(new Dimension(96, 96));
        imageLabel = new JLabel();
        imageLabel.setFont(new Font("Courier", 20, 40));
        this.addMouseListener(this);

        if(isOccupied()) {
            imageLabel.setText(piece.getImage());
           // imageLabel.setText("\u2654");
        } else {
            imageLabel.setText("");
        }

        this.add(imageLabel);

    }



    public void update() {
        if(isOccupied()) {
            imageLabel.setText(piece.getImage());
            //imageLabel.setText("\u2654");
        } else {
            imageLabel.setText("");
        }
        repaint();
    }


    public void setPiece(Piece piece) {
        this.piece = piece;
        setOccupied(true);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setColor(Color color) {
        this.setBackground(color);
    }

    public void returnOriginalColor() {
        this.setBackground(color);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if(controller.getGameInstance().isPieceSelected()) {
            controller.getCurrentPlayer().takeTurn(controller.getGameInstance().getFirstPos(), this.position);
            controller.getGameInstance().setPieceSelected(false);
            piece.setSelected(false);
            for(int row = 0; row < 8; row++) {
                for (int column = 0; column < 8; column++) {
                    controller.getCell(new Position(column, row)).returnOriginalColor();
                }
            }

        } else {
            if(isOccupied() && piece.getColor().equals(controller.getCurrentPlayer().getTeamColor())) {
                if(piece.isSelected()) {
                    this.setBackground(color);
                    this.piece.setSelected(false);
                    Iterator<Position> positionIterator = this.piece.getMovesList(position, controller.getGameInstance());
                    while (positionIterator.hasNext()) {
                        controller.getCell(positionIterator.next()).returnOriginalColor();
                    }

                } else {
                    color = this.getBackground();
                    this.setBackground(Color.CYAN);
                    this.piece.setSelected(true);
                    Iterator<Position> positionIterator = this.piece.getMovesList(position, controller.getGameInstance());
                    while (positionIterator.hasNext()) {
                        controller.getCell(positionIterator.next()).setColor(Color.GREEN);
                    }
                    controller.getGameInstance().setPieceSelected(true);
                    controller.getGameInstance().setSelectedPos(this.position);
                }


            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setOriginalColor(Color color) {
        this.color = color;
    }
}
