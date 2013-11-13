package edu.neumont.edu.jjensen.lab;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Jonathan
 * Date: 11/12/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class MoveInterpreter {
    //private final String MOVES_TESTFILE = "C:\\Users\\Jonathan\\TestFile.txt";
    private final String PIECEMOVE_PATTERN = "(?<pieceMove>(?<pieceType>[a-zA-Z])(?<color>[l,L,d,D])(?<location>[a-hA-H]{1}[1-8]))";
    private final String CAPTURE_PATTERN = "(?<capture>(?<piece>[a-h][1-8 ]) (?<capturePiece>[a-h][1-8])[*])";
    private final String CASTLING_PATTERN = "(?<castling>(?<piece1Pos1>[a-h][1-8 ]) (?<piece1Pos2>[a-h][1-8]) (?<piece2Pos1>[a-h][1-8]) (?<piece2Pos2>[a-h][1-8]))";
    private final String MOVES_PATTERN = "(?<move>(?<pos1>[a-h][1-8 ]) (?<pos2>[a-h][1-8]))";

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
            processMoves(movesIterator.next());
        }
    }

    private void processMoves(String move) {
        Pattern pattern = Pattern.compile(PIECEMOVE_PATTERN);
        Matcher patternMatcher = pattern.matcher(move);

        if(patternMatcher.matches()) {
            if(patternMatcher.group("pieceMove") != null ){
                setPiece(pattern, patternMatcher, move);
            }
        }
    }

    private void setPiece(Pattern pattern1, Matcher patternMatcher, String move) {
        Pattern pattern = Pattern.compile(PIECEMOVE_PATTERN);

        Matcher matcher = pattern.matcher(move);
        while(matcher.find()) {
            if(getPieceType(patternMatcher.group("pieceType")).equalsIgnoreCase("Invalid")) {
                System.out.println(patternMatcher.group("pieceType") + " is an invalid piece code");
            } else {
                System.out.println("Place the " + getPieceColor(patternMatcher.group("color")) + " "
                        + getPieceType(patternMatcher.group("pieceType")) + " on " + patternMatcher.group("location") );
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
