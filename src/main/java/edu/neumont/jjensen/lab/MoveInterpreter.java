package edu.neumont.jjensen.lab;

import edu.neumont.jjensen.lab.controller.Controller;
import edu.neumont.jjensen.lab.model.*;
import edu.neumont.jjensen.lab.model.pieces.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
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
                    castleingMove(move, patternMatcher);
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


    public void interpretMove(String move) {
        
        matchFound = false;
        castleingPerformed = false;

        for(int i = 0; i < patternMatchers.length; i++) {
            
            patternMatchers[i].performAction(move, patternMatchers[i]);
        }

        if(!matchFound) {
            
            output(move + " is not a valid move" + NEWLINE);
        }
        
    }


    private void setPiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);
        if(matcher.find()) {
            matchFound = true;
            Piece piece = getPieceType(matcher.group("pieceType")).getInstance();
            piece.setColor(getPieceColor(matcher.group("color")));
            piece.setAsciiImage();
            controller.setPiece(new Position(matcher.group("location").toUpperCase()), piece);


        }
    }

    private boolean isKingInCheck(TeamColor color) {
        boolean kingInCheck = false;
        for(int column = 0; !kingInCheck && column < controller.getBoardSize(); column++) {
            for(int row = 0; !kingInCheck && row < controller.getBoardSize(); row++) {
                Position pos = new Position(column, row);
                Cell cell = controller.getCell(pos);
                if(cell.isOccupied()) {
                    Piece piece = cell.getPiece();
                    Iterator<Position> iterator = piece.getMovesList(pos, controller);

                    while(iterator.hasNext() && !kingInCheck) {
                        Position pos2 = iterator.next();
                        kingInCheck = isKingInCell(pos2);
                        //output(pos2.toString());

                    }
                }
            }
        }

        return kingInCheck;
    }

    private boolean isKingInCell(Position pos) {
        boolean kingFound = false;
        Cell cell = controller.getCell(pos);
        if(cell.isOccupied()) {
            Piece piece = cell.getPiece();
            if(piece instanceof King) {
                kingFound = true;

            }

        }
        return kingFound;
    }


    private void movePiece(String move, PatternMatcher patternMatcher) {
        Matcher matcher = patternMatcher.getMatcher(move);

        if(!castleingPerformed) {
            while(matcher.find()) {
                matchFound = true;
                Position pos1Key = new Position(matcher.group("pos1").toUpperCase());
                Position pos2Key = new Position(matcher.group("pos2").toUpperCase());

                Cell srcCell = controller.getCell(pos1Key);
                 if(isKingInCheck(controller.getCurrentPlayer().getTeamColor())) {
                     output("King in check");
                 }

                if(srcCell.isOccupied()) {
                    Piece piece = srcCell.getPiece();
                    if(piece.isMoveValid(pos1Key, pos2Key, controller)) {
                        controller.setPiece(pos2Key, piece);
                        controller.getCell(pos1Key).removePiece();

                        controller.displayBoaard();
                        output("Press any key to continue...");
                        controller.endTurn();
                        new Scanner(System.in).nextLine();
                    } else {
                        output(move + " is an invalid move" + NEWLINE);
                    }


                } else {
                    output("No piece at " + pos1Key + NEWLINE);
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

    private void castleingMove(String move, PatternMatcher patternMatcher) {
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
