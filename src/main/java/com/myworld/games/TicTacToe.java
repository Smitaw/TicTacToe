package com.myworld.games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.myworld.games.GameEngine.WinConfig;

// Strategy pattern.
// The computer player's behavior/strategy can be replaced by inheriting from the interface below
// Also, the human player's behavior inherits from the same interface
// This also makes it easy to modify the game for 2 human players, 2 computer players etc.

public class TicTacToe {

    static final String SLASH = " | ";
    protected static final int N = 3;
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;
    private Board ticTacToeBoard;

    private Player currentPlayer;
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private Player playerCROSS, playerNOUGHT;

    public Player getplayer1() {
        return playerCROSS;
    }

    public Player getplayer2() {
        return playerNOUGHT;
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
        ticTacToeBoard = new Board();
        initialisePlayers();
    }

    private void initialisePlayers() {
        System.out.println("Enter player's choice human (2) or computer (1)..");
        String choice = getUserInput();

        System.out.println("Enter player 1 name");
        String player1Name = getUserInput();
        String player2Name = new String();
        playerCROSS = new Player(player1Name, CROSS, new HumanMove(this));
        if (choice.equals("2")) {
            System.out.println("Enter player 2 name");
            player2Name = getUserInput();
            while (player1Name.equals(player2Name)) {
                System.out.println("Please enter different names ");
                player2Name = getUserInput();
            }
            playerNOUGHT = new Player(player2Name, NOUGHT, new HumanMove(this));
            System.out.println("\nPlayer 1 " + playerCROSS.getName() + " vs Player 2 " + playerNOUGHT.getName() + ":");
        } else {
            playerNOUGHT = new Player("Computer", NOUGHT, new SimpleMoveStrategy(this));
            System.out.println("\nHuman player " + playerCROSS.getName() + " vs Computer Player :");
        }

    }

    public boolean setMove(int move, int p_type) {
        int x_coord = (move - 1) / 3;
        int y_coord = (move - 1) % 3;
        int[][] board = getBoard();
        if (board[x_coord][y_coord] == 0) {
            board[x_coord][y_coord] = p_type;
            return true;
        }
        System.out.println("Invalid move");
        return false;

    }

    protected int[][] getBoard() {
        return ticTacToeBoard.get();
    }

    private static int updatePlayerMove(TicTacToe game) {
        int move = 0;
        System.out.println("Player " + game.getCurrentPlayer().getName() + "\'s turn");
        do {
            move = game.getCurrentPlayer().getMove();
        } while (!game.setMove(move, game.getCurrentPlayer().getPlayerType()));
        System.out.println("\n Player " + game.getCurrentPlayer().getName() + " has added in the "
                + Formatter.getPosDescription(move));
        System.out.println(game.display());
        return move;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player current) {
        currentPlayer = current;
    }

    protected String display() {
        return ticTacToeBoard.display();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Tic-Tac-Toe \n");

        TicTacToe game = new TicTacToe();

        System.out.println(
                "Please make your move selection by entering a number 1-9 corresponding to the movement key on the right.\n");
        System.out.println(game.display());

        game.setCurrentPlayer(game.getplayer1());
        WinConfig winConfig = WinConfig.NONE;
        while (winConfig == WinConfig.NONE) {

            int currentMove = updatePlayerMove(game);
            winConfig = GameEngine.isWinningConfig(game.getBoard(), currentMove);
            if (winConfig == WinConfig.WIN) {
                System.out.println("\n" + game.getCurrentPlayer().getName() + " have won!");
                break;
            } else if (winConfig == WinConfig.DRAW) {
                System.out.println("\n Well played. It is a draw!");
                break;
            }
            if (game.getCurrentPlayer() == game.getplayer1()) {
                game.setCurrentPlayer(game.getplayer2());
            } else {
                game.setCurrentPlayer(game.getplayer1());
            }
        }

    }

}
