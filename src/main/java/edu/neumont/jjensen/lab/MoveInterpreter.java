package edu.neumont.jjensen.lab;

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

    private PatternMatcher[] patternMatchers ={
            new PatternMatcher("(?<pieceMove>(?<pieceType>[a-zA-Z])(?<color>[l,L,d,D])(?<location>[a-hA-H]{1}[1-8]))", new Actionable() {
                @Override
                public void performAction(String move, PatternMatcher patternMatcher) {
                    setPiece(move, patternMatcher);

                }
            }),

            new PatternMatcher("(?<castling>(?<piece1Pos1>[a-h][1-8 ]) (?<piece1Pos2>[a-h][1-8]) (?<piece2Pos1>[a-h][1-8]) (?<piece2Pos2>[a-h][1-8]))", new Actionable() {
                @Override
                public void performAction(String move, PatternMatcher patternMatcher) {
                    castlingMove(move, patternMatcher);
                }
            }),

            new PatternMatcher("(?<move>(?<pos1>^[a-h][1-8 ]) (?<pos2>[a-h][1-8]))", new Actionable() {
                @Override
                public void performAction(String move, PatternMatcher patternMatcher) {
                    movePiece(move, patternMatcher);
                }
            }),

            new PatternMatcher("(?<capture>(?<piece>[a-h][1-8 ]) (?<capturePiece>[a-h][1-8])[*])", new Actionable() {
                @Override
                public void performAction(String move, PatternMatcher patternMatcher) {
                    capturePiece(move, patternMatcher);
                }
            })


    };

    private boolean matchFound = false;
    private boolean castleingPerformed = false;
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
            matchFound = false;
            castleingPerformed = false;

            for(int i = 0; i < patternMatchers.length; i++) {
                patternMatchers[i].performAction(move, patternMatchers[i]);
            }

            if(!matchFound) {
                output(move + " is not a valid move");
            }
        }
    }


    private void setPiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);
        while(matcher.find()) {
            matchFound = true;
            if(getPieceType(matcher.group("pieceType")).equalsIgnoreCase("Invalid")) {
                output(matcher.group("pieceType") + " is an invalid piece code");
            } else {
                output("Place the " + getPieceColor(matcher.group("color")) + " "
                        + getPieceType(matcher.group("pieceType")) + " on " + matcher.group("location"));
            }

        }
    }

    private void movePiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);

        if(!castleingPerformed) {
            while(matcher.find()) {
                matchFound = true;
                output("Piece at " + matcher.group("pos1") + " moved to " + matcher.group("pos2"));

            }
        }

    }

    private void capturePiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);

        while(matcher.find()) {
            matchFound = true;
            output("Piece at " + matcher.group("piece") + " captures piece at " + matcher.group("capturePiece"));
        }
    }

    private void castlingMove(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);
        if(matcher.find()) {
            castleingPerformed = true;
            while(matcher.find()) {
                matchFound = true;
                output("Piece at " + matcher.group("piece1Pos1") + " moves to cell " + matcher.group("piece1Pos2")
                    + " and piece at " + matcher.group("piece2Pos1") + " moves to cell " + matcher.group("piece2Pos2"));
            }

        }
    }

    private void output(String message) {
        System.out.println(message);
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
