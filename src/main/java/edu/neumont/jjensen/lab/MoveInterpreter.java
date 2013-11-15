package edu.neumont.jjensen.lab;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/12/13
 * Time: 1:41 PM
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

            new PatternMatcher("(?<move>(?<pos1>[a-h][1-8 ]) (?<pos2>[a-h][1-8]))", new Actionable() {
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

    private HashMap<String, String> pieceTypes;

    private boolean matchFound;
    private boolean castleingPerformed;
    private String filePath;

    public MoveInterpreter(String filePath) {
        this.filePath = filePath;
        setupPieceTypes();
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

            output("Place the " + getPieceColor(matcher.group("color")) + " "
                  + getPieceType(matcher.group("pieceType")) + " on " + matcher.group("location"));

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
            matcher.reset();
            castleingPerformed = true;
            while(matcher.find()) {
                matchFound = true;
                output(getCastleingOutput( matcher.group("piece1Pos1"), matcher.group("piece1Pos2")) +
                        getCastleingOutput( matcher.group("piece2Pos1"), matcher.group("piece2Pos2")));
            }

        }
    }

    private String getCastleingOutput(String position1, String position2) {
        return "Piece at " + position1 + " moves to cell " + position2 + " ";
    }

    private void output(String message) {
        System.out.println(message);
    }

    private Iterator<String> getMoves() {
        FileInputReader fileReader = new FileInputReader(filePath);
        fileReader.readInFile();
        return fileReader.getMovesList();
    }

    private void setupPieceTypes() {
        pieceTypes = new HashMap<>();
        pieceTypes.put("r", "Rook");
        pieceTypes.put("q", "Queen");
        pieceTypes.put("b", "Bishop");
        pieceTypes.put("n", "Knight");
        pieceTypes.put("k", "King");
        pieceTypes.put("p", "Pawn");
    }

    private String getPieceType(String pieceCode) {

        return pieceTypes.get(pieceCode);

    }

}
