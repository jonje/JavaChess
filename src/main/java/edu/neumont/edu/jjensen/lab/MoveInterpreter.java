package edu.neumont.edu.jjensen.lab;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveInterpreter {
    private final String PIECEMOVE_PATTERN = "(?<pieceMove>(?<pieceType>[a-zA-Z])(?<color>[l,L,d,D])(?<location>[a-h]{1}[1-8]))";

    private String getPieceColor(String piece) {
        return (piece.equalsIgnoreCase("l")) ? "White" : "Black";

    }

    private void processMoves(String move) {


    }



}
