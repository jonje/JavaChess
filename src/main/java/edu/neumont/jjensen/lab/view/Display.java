package edu.neumont.jjensen.lab.view;

import edu.neumont.jjensen.lab.controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/15/13
 * Time: 2:45 PM
 */
public class Display extends JPanel {
    private Controller controller;
    private final String PADDING = "\n";


    public Display(Controller controller) {
        this.controller = controller;
        this.setSize(new Dimension(200, 200));

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        //displayBoard(graphics2D);
        boolean isWhite = false;
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

        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public void displayBoard(Graphics2D graphics2D) {
        boolean isWhite = false;
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

        }
    }

    public void displayBoard() {
     /*   for(int row = 0; row < controller.getBoardSize(); row++) {

            for(int column =0; column < controller.getBoardSize(); column++) {

                Cell cell = controller.getCell(new Position(column, row));

                String emptyCell = "[ - ]";

                 output += (cell.isOccupied()) ?  "[ " + cell.getPiece().getAsciiImage() + " ]" :  emptyCell;


            }
            System.out.println(output);

        }


     */
    }

    public Controller getController() {
        return controller;
    }


}
