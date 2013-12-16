package edu.neumont.jjensen.lab.model;

import edu.neumont.jjensen.lab.model.pieces.King;

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

    public void takeTurn() {
        displayMessage("Select piece:(Ex. A7)");
        String userInput = getUserInput();
        Position selectedPosition = null;
        if(isValidInput(userInput) && game.getCell(getPosition(userInput)).isOccupied()) {
            selectedPosition = getPosition(userInput);
            Piece piece = game.getCell(selectedPosition).getPiece();
            String moves = "";
            Iterator<Position> possibleMoves = piece.getMovesList(selectedPosition, game);
            while(possibleMoves.hasNext()) {
                moves += "[" + possibleMoves.next().toString() + "]";

            }

            if(moves.length() > 0) {
                displayMessage(moves);
                displayMessage("Enter position to move to:");
                if(performMove(selectedPosition)) {
                    game.endTurn();
                }
            }




        }


    }

    private boolean performMove(Position selectedPosition) {
        boolean performed = false;
        String userInput = getUserInput();
        if(isValidInput(userInput)) {
            String move = selectedPosition.toString() + " " + new Position(userInput).toString();
            performed = game.performMove(move);

        }
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
            //game.dumpBoard();
            if(game.performMove(move)){
                if (!isKingInCheck()) {
                    listKingProtectorMoves.add(move);

                }

                move = destPos.toString() + " " + tempPos.toString();
                game.performMove(move);
            }


        }
        return listKingProtectorMoves;
    }


}
