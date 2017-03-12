package com.myworld.games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// Strategy pattern.
// The computer player's behavior/strategy can be replaced by inheriting from the interface below
// Also, the human player's behavior inherits from the same interface
// This also makes it easy to modify the game for 2 human players, 2 computer players etc.

public class TicTacToe {
    private static final String CROSS_STRING = "X";
    private static final String NOUGHT_STRING = "O";
    private static final String SLASH = " | ";
    protected static final int N = 3;
    private static final int HSPACE = 20;
    protected int[][] board;
    private Player currentPlayer;
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public enum PlayerTypes {
        NONE, CROSS, NOUGHT;
    }

    private Player player1, player2;

    public Player getplayer1() {
        return player1;
    }

    public Player getplayer2() {
        return player2;
    }

    protected static String getUserInput() {
        String input = new String();
        while (input.isEmpty()) {
            try {
                input = reader.readLine();
            } catch (IOException ex) {
                System.out.println("Some error in getting input, let's try again !!");
            }
        }
        return input;
    }

    public TicTacToe() {
        initialiseBoard();
        initialisePlayers();
    }

    private void initialisePlayers() {
        System.out.println("Enter player's choice human (2) or computer (1)..");
        String choice = getUserInput();

        System.out.println("Enter player 1 name");
        String player1Name = getUserInput();
        String player2Name = new String();
        player1 = new Player(player1Name, PlayerTypes.NOUGHT.ordinal(), new HumanMove(this));
        if (choice.equals("2")) {
            System.out.println("Enter player 2 name");
            player2Name = getUserInput();
            while (player1Name.equals(player2Name)) {
                System.out.println("Please enter different names ");
                player2Name = getUserInput();
            }
            player2 = new Player(player2Name, PlayerTypes.CROSS.ordinal(), new HumanMove(this));
            System.out.println("\nPlayer 1 " + player1.getName() + " vs Player 2 " + player2.getName() + ":");
        } else {
            player2 = new Player("Compu", PlayerTypes.CROSS.ordinal(), new SimpleMoveStrategy(this));
            System.out.println("\nHuman player " + player1.getName() + " vs Computer Player :");
        }

    }

    private void initialiseBoard() {
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = 0;
            }
        }
    }

    public boolean setMove(int move, int p_type) {
        int x_coord = (move - 1) / 3;
        int y_coord = (move - 1) % 3;

        if (board[x_coord][y_coord] == 0) {
            board[x_coord][y_coord] = p_type;
            return true;
        }
        System.out.println("Invalid move");
        return false;

    }

    private enum WinConfig {
        DRAW, WIN, NONE
    }

    private WinConfig isWinningConfig() {
        WinConfig w = WinConfig.WIN;
        // rows
        for (int i = 0; i < N; i++) {
            if ((board[i][0] != 0) && (board[i][0] == board[i][1]) && (board[i][0] == board[i][2])) {
                return w;
            }
        }
        // columns
        for (int i = 0; i < N; i++) {
            if ((board[0][i] != 0) && (board[0][i] == board[1][i]) && (board[0][i] == board[2][i])) {
                return w;
            }
        }
        // diags
        if ((board[0][0] != 0) && (board[0][0] == board[1][1]) && (board[0][0] == board[2][2])) {
            return w;
        }

        if ((board[2][0] != 0) && (board[2][0] == board[1][1]) && (board[2][0] == board[0][2])) {
            return w;
        }

        // draw
        w = WinConfig.DRAW;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0)
                    w = WinConfig.NONE;
            }
        }
        return w;

    }

    private String getRowString(int row) {

        String s = "";
        for (int i = 0; i < N; i++) {
            switch (board[row][i]) {
            case 0:
                s += " ";
                break;
            case 1:
                s += NOUGHT_STRING;
                break;
            case 2:
                s += CROSS_STRING;
            }

            if (i != N - 1) {
                s += SLASH;
            }
        }

        s += String.format("%" + HSPACE + "s", "");

        for (int i = 0; i < N; i++) {
            s += row * 3 + i + 1;

            if (i == N - 1) {
                s += "\n";
            } else {
                s += SLASH;
            }
        }
        return s;
    }

    public String toString() {
        String s = "";
        // iterate through the rows
        for (int i = 0; i < N; i++) {
            s += getRowString(i);
        }
        return s;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player current) {
        currentPlayer = current;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe \n");

        TicTacToe game = new TicTacToe();
        WinConfig w = WinConfig.NONE;

        System.out.println(
                "Please make your move selection by entering a number 1-9 corresponding to the movement key on the right.\n");
        System.out.println(game.toString());

        game.setCurrentPlayer(game.getplayer1());
        while (game.isWinningConfig() == WinConfig.NONE) {

            updatePlayerMove(game);

            w = game.isWinningConfig();
            if (w == WinConfig.WIN) {
                System.out.println("");
                System.out.println(game.getCurrentPlayer().getName() + " have won!");
                //System.out.println(game.toString());
                break;
            } else if (w == WinConfig.DRAW) {
                System.out.println("");
                System.out.println("Well played. It is a draw!");
                //System.out.println(game.toString());
                break;
            }
            if (game.getCurrentPlayer() == game.getplayer1()) {
                game.setCurrentPlayer(game.getplayer2());
            } else {
                game.setCurrentPlayer(game.getplayer1());
            }
        }

    }

    private static void updatePlayerMove(TicTacToe game) {
        int move = 0;
        System.out.println("Player " + game.getCurrentPlayer().getName() + "\'s turn");
        do {
            move = game.getCurrentPlayer().getMove();
        } while (!game.setMove(move, game.getCurrentPlayer().getPlayerType()));
        System.out.println("");
        System.out.println("Player " + game.getCurrentPlayer().getName() + " has added in the "
                + Formatter.getPosDescription(move));
        System.out.println(game.toString());
    }
}
