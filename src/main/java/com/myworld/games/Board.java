package com.myworld.games;

public class Board {

    private static final int N = TicTacToe.N;
    protected int[][] board;
    private static final String CROSS_STRING = "X";
    private static final String NOUGHT_STRING = "O";
    static final String SLASH = " | ";
    private static final int HSPACE = 20;
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;

    public Board() {
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
    }

    protected int[][] get() {
        return board;
    }

    protected String getRowString(int row) {
        String displayRow = getBoardRow(row);
        displayRow += String.format("%" + HSPACE + "s", "");
        displayRow += Formatter.getMessageRow(row);
        return displayRow;
    }

    protected String getBoardRow(int row) {
        String s = new String();
        for (int i = 0; i < N; i++) {
            switch (board[row][i]) {
            case 0:
                s += " ";
                break;
            case NOUGHT:
                s += NOUGHT_STRING;
                break;
            case CROSS:
                s += CROSS_STRING;
            default:
                break;
            }

            if (i != N - 1) {
                s += SLASH;
            }
        }
        return s;
    }

    public String display() {
        String s = new String();
        // iterate through the rows
        for (int i = 0; i < N; i++) {
            s += getRowString(i);
        }
        return s;
    }

}
