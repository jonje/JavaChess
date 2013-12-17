package edu.neumont.jjensen.lab.view;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/15/13
 * Time: 2:45 PM
 */
public class Display extends JPanel implements MouseListener{
    private Controller controller;
    private final String PADDING = "\n";


    public Display(Controller controller) {
        this.setLayout(new GridLayout(8,8));
        this.controller = controller;
        this.setSize(new Dimension(1000, 1000));
        setupBoard();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //displayBoard(graphics2D);
        /*boolean isWhite = false;
        for(int row = 0;  row < 8; row++) {
            for(int column =0; column < 8; column++) {
                if(isWhite) {
                    graphics2D.setColor(Color.WHITE);
                    isWhite = false;
                } else {
                    graphics2D.setBackground(Color.BLACK);
                    isWhite = true;
                }
                int x = column * 64;
                int y = row * 64;
                graphics2D.fillRect(x, y, 64, 64);

            }

        }*/
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public void setupBoard() {

        for(int row = 0;  row < 8; row++) {
            for(int column =0; column < 8; column++) {
                Cell cell = controller.getCell(new Position(column, row));
                if(row % 2 == column % 2) {
                    cell.setBackground(Color.WHITE);
                    cell.setOriginalColor(Color.WHITE);

                } else {
                    cell.setBackground(Color.BLACK);
                    cell.setOriginalColor(Color.BLACK);
                }
                this.add(cell);
            }

        }
    }

    public void displayBoard() {
        String output = "";

        for(int row = 0; row < controller.getBoardSize(); row++) {

            for(int column =0; column < controller.getBoardSize(); column++) {

                Cell cell = controller.getCell(new Position(column, row));

                String emptyCell = "[ - ]";

                 output += (cell.isOccupied()) ?  "[ " + cell.getPiece().getAsciiImage() + " ]" :  emptyCell;


            }
            System.out.println(output);

        }


    }

    public Controller getController() {
        return controller;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Pressed");

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
}
