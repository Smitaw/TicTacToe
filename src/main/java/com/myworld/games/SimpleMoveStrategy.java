package com.myworld.games;

public class SimpleMoveStrategy implements MoveMethod {
    private TicTacToe game;

    public SimpleMoveStrategy(TicTacToe t) {
        game = t;
    }

    public int move() {

        for (int i = 0; i < TicTacToe.N; i++) {
            for (int j = 0; j < TicTacToe.N; j++) {
                if (game.board[i][j] == 0)
                    // N and 3 interchangebly used.
                    return (i * 3 + j + 1);
            }
        }
        return 0;
    }
}