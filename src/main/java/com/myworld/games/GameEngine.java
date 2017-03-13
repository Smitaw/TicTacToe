package com.myworld.games;

public class GameEngine {
    private static final int N = TicTacToe.N;

    public enum WinConfig {
        DRAW, WIN, NONE
    }

    public static WinConfig isWinningConfig(int[][] board, int currentMove) {
        if (currentMove == 0) {
            return WinConfig.NONE;
        }
        int currentRow = (currentMove - 1) / 3;
        int currentCol = (currentMove - 1) % 3;

        if ((board[currentRow][0] == board[currentRow][1] && board[currentRow][1] == board[currentRow][2])
                || (board[0][currentCol] == board[1][currentCol] && board[2][currentCol] == board[1][currentCol])
                || (currentRow == currentCol && (board[0][0] != 0) && board[0][0] == board[1][1]
                        && board[2][2] == board[1][1])
                || (currentRow + currentCol == 2 && (board[2][0] != 0) && (board[2][0] == board[1][1])
                        && (board[2][0] == board[0][2]))) {
            return WinConfig.WIN;
        }

        if (isDraw(board)) {
            return WinConfig.DRAW;
        }
        return WinConfig.NONE;

    }

    public static boolean isDraw(int[][] board) {
        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                if (board[row][col] == 0) {
                    // return from here itself.
                    return false; // an empty cell found, not draw, exit
                }
            }
        }
        return true; // no empty cell, it's a draw
    }

}
