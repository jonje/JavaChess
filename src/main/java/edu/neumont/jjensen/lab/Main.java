package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;
import edu.neumont.jjensen.lab.view.Display;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:11 PM
 */
public class Main {
    public static void main(String[] args) {
        Display display = new Display();
        FileInputReader inputReader = new FileInputReader(display.getController());
        for(String argument: args) {
            inputReader.readInFile(argument);

        }

        Piece rook = display.getController().getCell(new Position('A', '8')).getPiece();
        Iterator<String> moves = rook.getMovesList(new Position('A', '8'), display.getController());

        while(moves.hasNext()) {
            System.out.println(moves.next());
        }


    }

}
