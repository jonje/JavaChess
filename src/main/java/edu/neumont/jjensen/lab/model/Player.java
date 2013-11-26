package edu.neumont.jjensen.lab.model;

/**
 * Created with IntelliJ IDEA.
 * User: jjensen
 * Date: 11/26/13
 * Time: 1:04 PM
 */
public class Player {
    TeamColor color;

    public Player(TeamColor color) {
        this.color = color;

    }

    public TeamColor getTeamColor() {
        return color;
    }

}
