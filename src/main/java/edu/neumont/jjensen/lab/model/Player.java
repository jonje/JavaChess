package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.model.pieces.King;
import edu.neumont.jjensen.lab.model.pieces.Pawn;
import edu.neumont.jjensen.lab.model.pieces.Queen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/26/13
 * Time: 1:04 PM
 */
public class Player {
    private Scanner inputStream;

    private TeamColor color;
    private ChessGame game;
    private List<String> kingProtectorMoves;

    public Player(TeamColor color, ChessGame game) {
        this.color = color;
        this.game = game;
        kingProtectorMoves = new ArrayList<>();
        inputStream = new Scanner(System.in);

    }

    public TeamColor getTeamColor() {
        return color;
    }

    public boolean isKingInCheck() {
        boolean kingInCheck = false;
        for (int column = 0; !kingInCheck && column < game.getBoardSize(); column++) {
            for (int row = 0; !kingInCheck && row < game.getBoardSize(); row++) {
                Position pos = new Position(column, row);
                Cell cell = game.getCell(pos);
                if (cell.isOccupied()) {
                    Piece piece = cell.getPiece();

                    if (!piece.getColor().equals(getTeamColor())) {
                        Iterator<Position> iterator = piece.getMovesList(pos, game);

                        while (iterator.hasNext() && !kingInCheck) {
                            Position pos2 = iterator.next();
                            kingInCheck = isKingInCell(pos2);
                        }
                    }

                }
            }
        }

        return kingInCheck;
    }

    public void takeTurn(Position selectedPosition, Position destPos) {

            Piece piece = game.getCell(selectedPosition).getPiece();

                if(performMove(selectedPosition, destPos)) {
                    changePawn(piece);
                    checkForPawnPromotion(piece, selectedPosition, game);
                    game.endTurn();
                }








    }

    private void checkForPawnPromotion(Piece piece, Position pos, ChessGame game) {
        if(piece instanceof Pawn) {
            if(piece.getColor().equals(TeamColor.WHITE)){
               if(pos.getRow() == '1') {
                   createQueen(pos, piece, game);
               }
            } else {
                if(pos.getRow() == '8') {
                   createQueen(pos, piece, game);
                }
            }

        }
    }

    private void createQueen(Position pos, Piece piece, ChessGame game) {
        Piece queen = new Queen();
        queen.setColor(piece.getColor());
        game.getCell(pos).setPiece(queen);


    }

    private void changePawn(Piece piece) {
        if((piece instanceof Pawn) && ((Pawn) piece).isFirstTurn()) {
            ((Pawn) piece).setIsFirstTurn(false);

        }
    }

    private boolean performMove(Position selectedPosition, Position destPos) {
        boolean performed = false;

            String move = selectedPosition.toString() + " " + destPos.toString();
            performed = game.performMove(move);


        return performed;
    }

    private String getUserInput() {
        return inputStream.nextLine();
    }

    private boolean isValidInput(String input) {
        return (input.length() == 2);
    }

    private Position getPosition(String pos) {
        return new Position(pos);

    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public Iterator<String> getProtectorMoves() {
        return kingProtectorMoves.iterator();
    }

    private boolean isKingInCell(Position pos) {
        boolean kingFound = false;
        Cell cell = game.getCell(pos);
        if (cell.isOccupied()) {
            Piece piece = cell.getPiece();
            if (piece instanceof King && piece.getColor().equals(color)) {
                kingFound = true;

            }

        }
        return kingFound;
    }

    public boolean isKingProtectable() {
        System.out.println("Reached isKing Protectable");
        kingProtectorMoves = new ArrayList<>();

        boolean kingProtectable = false;
        if (isKingInCheck()) {
            for (int column = 0; column < game.getBoardSize(); column++) {
                for (int row = 0; row < game.getBoardSize(); row++) {

                    Position tempPos = new Position(column, row);
                    Cell cell = game.getCell(tempPos);

                    if (cell.isOccupied()) {
                        Piece piece = cell.getPiece();
                        if (piece.getColor().equals(getTeamColor())) {
                            kingProtectorMoves.addAll(tryMoves(tempPos, piece));

                        }
                    }
                }
            }
            kingProtectable = (kingProtectorMoves.size() > 0);
        }

        return kingProtectable;
    }

    private List<String> tryMoves(final Position tempPos, final Piece piece) {
        List<String> listKingProtectorMoves = new ArrayList<>();
        Iterator<Position> moves = piece.getMovesList(tempPos, game);

        while (moves.hasNext()) {
            Position destPos = moves.next();
            String move = tempPos.toString() + " " + destPos.toString();

            if(game.performMove(move)){
                if (!isKingInCheck()) {
                    listKingProtectorMoves.add(move);

                }


                game.forceMove(destPos, tempPos);
            }


        }
        return listKingProtectorMoves;
    }


}
