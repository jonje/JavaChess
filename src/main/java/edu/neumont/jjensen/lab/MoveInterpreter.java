package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.Cell;
import edu.neumont.jjensen.lab.model.Piece;
import edu.neumont.jjensen.lab.model.Position;
import edu.neumont.jjensen.lab.model.TeamColor;
import edu.neumont.jjensen.lab.model.pieces.*;

import java.util.HashMap;
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
                public boolean performAction(String move, PatternMatcher patternMatcher) {
                    return setPiece(move, patternMatcher);

                }
            }),

            new PatternMatcher("(?<castling>(?<piece1Pos1>[a-h][1-8 ]) (?<piece1Pos2>[a-h][1-8]) (?<piece2Pos1>[a-h][1-8]) (?<piece2Pos2>[a-h][1-8]))", new Actionable() {
                @Override
                public boolean performAction(String move, PatternMatcher patternMatcher) {
                    return castleingMove(move, patternMatcher);
                }
            }),

            new PatternMatcher("(?<move>(?<pos1>[a-hA-H][1-8 ]) (?<pos2>[a-hA-H][1-8]))", new Actionable() {
                @Override
                public boolean performAction(String move, PatternMatcher patternMatcher) {
                    return movePiece(move, patternMatcher);
                }
            }),

            new PatternMatcher("(?<capture>(?<piece>[a-h][1-8 ]) (?<capturePiece>[a-h][1-8])[*])", new Actionable() {
                @Override
                public boolean performAction(String move, PatternMatcher patternMatcher) {
                    return capturePiece(move, patternMatcher);
                }
            })


    };

    private final String NEWLINE = "\n";

    private HashMap<String, Piece> pieceTypes;

    private boolean matchFound;
    private boolean castleingPerformed;
  

    private Controller controller;

    public MoveInterpreter (Controller controller) {
        
        this.controller = controller;

        setupPieceTypes();
    }


    private TeamColor getPieceColor(String piece) {
        return (piece.equalsIgnoreCase("l")) ? TeamColor.WHITE : TeamColor.BLACK;

    }


    public boolean interpretMove(String move) {
        boolean isValid = false;
        matchFound = false;
        castleingPerformed = false;

        for (PatternMatcher patternMatcher : patternMatchers) {

            if(patternMatcher.performAction(move, patternMatcher)){
                isValid = true;
            }
        }

        if(!matchFound) {
            
            output(move + " is not a valid move" + NEWLINE);
        }
        return isValid;
    }


    private boolean setPiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);
        if(matcher.find()) {
            matchFound = true;
            Piece piece = getPieceType(matcher.group("pieceType")).getInstance();
            piece.setColor(getPieceColor(matcher.group("color")));
            piece.setAsciiImage();
            controller.setPiece(new Position(matcher.group("location").toUpperCase()), piece);


        }
        return false; //TODO fix this
    }



    private boolean movePiece(String move, PatternMatcher patternMatcher) {
        boolean movePerformed = false;
        Matcher matcher = patternMatcher.getMatcher(move);

        if(!castleingPerformed) {

            while(matcher.find()) {
                matchFound = true;
                Position pos1Key = new Position(matcher.group("pos1").toUpperCase());
                Position pos2Key = new Position(matcher.group("pos2").toUpperCase());

                Cell srcCell = controller.getCell(pos1Key);

                if(srcCell.isOccupied()) {
                    Piece piece = srcCell.getPiece();
                    if(piece.isMoveValid(pos1Key, pos2Key, controller.getGameInstance())) {
                        controller.setPiece(pos2Key, piece);
                        controller.getCell(pos1Key).removePiece();
                        movePerformed = true;

                    } else {
                        output(move + " is an invalid move" + NEWLINE);
                    }


                } else {
                    output("No piece at " + pos1Key + NEWLINE);
                }

            }
        }
        return movePerformed;

    }



    private boolean capturePiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);

        while(matcher.find()) {
            matchFound = true;
            output("Piece at " + matcher.group("piece") + " captures piece at " + matcher.group("capturePiece"));
        }

        return false; //todo must fix later
    }

    private boolean castleingMove(String move, PatternMatcher patternMatcher) {
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

        return false; //TODO fix later
    }

    private String getCastleingOutput(String position1, String position2) {
        return "Piece at " + position1 + " moves to cell " + position2 + " ";
    }

    private void output(String message) {
        System.out.println(message);
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
