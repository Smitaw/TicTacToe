package com.myworld.games;

public class Player {
    private String name;
    private int player_type;
    private MoveMethod move_strategy;

    public Player(String pname, int type, MoveMethod move_s) {
        name = pname;
        player_type = type;
        move_strategy = move_s;
    }

    public String getName() {
        return name;
    }

    public int getPlayerType() {
        return player_type;
    }

    public int getMove() {
        return move_strategy.move();
    }

}
