package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Color;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.pieces.*;

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

    private HashMap<String, Piece> pieceTypes;

    private boolean matchFound;
    private boolean castleingPerformed;
    private String filePath;

    private Controller controller;

    public MoveInterpreter(String filePath, Controller controller) {
        this.filePath = filePath;
        this.controller = controller;

        setupPieceTypes();
    }

    public void run() {
        iterateThroughMoves();
    }

    private Color getPieceColor(String piece) {
        return (piece.equalsIgnoreCase("l")) ? Color.WHITE : Color.BLACK;

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
            Piece piece = getPieceType(matcher.group("pieceType"));
            piece.setColor(getPieceColor(matcher.group("color")));
            piece.setAsciiImage();
            controller.setPiece(matcher.group("location"), piece);


        }
    }

    private void movePiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);

        if(!castleingPerformed) {
            while(matcher.find()) {
                matchFound = true;
                String pos1Key = matcher.group("pos1").toUpperCase();

                if(controller.getCell(pos1Key).isOccupied()) {
                    Piece piece = controller.getCell(pos1Key).getPiece();
                    controller.setPiece(matcher.group("pos2").toUpperCase(), piece);
                    controller.getCell(pos1Key).removePiece();
                } else {
                    output("No piece at " + pos1Key);
                }


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
        pieceTypes.put("R", new Rook());
        pieceTypes.put("Q", new Queen());
        pieceTypes.put("B", new Bishop());
        pieceTypes.put("N", new Knight());
        pieceTypes.put("K", new King());
        pieceTypes.put("P", new Pawn());
    }

    private Piece getPieceType(String pieceCode) {

        return pieceTypes.get(pieceCode);

    }

}
