package edu.neumont.edu.jjensen.lab;

import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveInterpreter {
    //private final String MOVES_TESTFILE = "C:\\Users\\Jonathan\\TestFile.txt";
    //private final String PIECEMOVE_PATTERN = "(?<pieceMove>(?<pieceType>[a-zA-Z])(?<color>[l,L,d,D])(?<location>[a-hA-H]{1}[1-8]))";
    //private final String CAPTURE_PATTERN = "(?<capture>(?<piece>[a-h][1-8 ]) (?<capturePiece>[a-h][1-8])[*])";
    //private final String CASTLING_PATTERN = "(?<castling>(?<piece1Pos1>[a-h][1-8 ]) (?<piece1Pos2>[a-h][1-8]) (?<piece2Pos1>[a-h][1-8]) (?<piece2Pos2>[a-h][1-8]))";
    //private final String MOVES_PATTERN = "(?<move>(?<pos1>[a-h][1-8 ]) (?<pos2>[a-h][1-8]))";

    private PatternMatcher[] patternMatchers ={
            new PatternMatcher("(?<pieceMove>(?<pieceType>[a-zA-Z])(?<color>[l,L,d,D])(?<location>[a-hA-H]{1}[1-8]))", new Actionable() {
                @Override
                public void performAction(String move, PatternMatcher patternMatcher) {
                    setPiece(move, patternMatcher);

                }
            })
    };

    private String filePath;

    public MoveInterpreter(String filePath) {
        this.filePath = filePath;
    }

    public void run() {
        iterateThroughMoves();
    }

    private String getPieceColor(String piece) {
        return (piece.equalsIgnoreCase("l")) ? "White" : "Black";

    }


    public void iterateThroughMoves() {
        Iterator<String> movesIterator = getMoves();

        while(movesIterator.hasNext()) {
            String move = movesIterator.next();
            for(int i = 0; i < patternMatchers.length; i++) {
                patternMatchers[i].performAction(move, patternMatchers[i]);
            }
        }
    }


    private void setPiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);
        while(matcher.find()) {
            if(getPieceType(matcher.group("pieceType")).equalsIgnoreCase("Invalid")) {
                System.out.println(matcher.group("pieceType") + " is an invalid piece code");
            } else {
                System.out.println("Place the " + getPieceColor(matcher.group("color")) + " "
                        + getPieceType(matcher.group("pieceType")) + " on " + matcher.group("location") );
            }

        }
    }

    private Iterator<String> getMoves() {
        FileInputReader fileReader = new FileInputReader(filePath);
        fileReader.readInFile();
        return fileReader.getMovesList();
    }

    private String getPieceType(String pieceCode) {
        String pieceType;
        if(pieceCode.equalsIgnoreCase("r")) {
            pieceType = "Rook";

        } else if(pieceCode.equalsIgnoreCase("q")) {
            pieceType = "Queen";

        } else if(pieceCode.equalsIgnoreCase("b")) {
            pieceType = "Bishop";

        } else if(pieceCode.equalsIgnoreCase("n")) {
            pieceType = "Knight";

        } else if(pieceCode.equalsIgnoreCase("k")) {
            pieceType = "King";

        } else if(pieceCode.equalsIgnoreCase("p")) {
            pieceType = "Pawn";
        } else {
            pieceType = "Invalid";
        }

        return pieceType;

    }



}
