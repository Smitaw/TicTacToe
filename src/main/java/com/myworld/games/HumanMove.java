package com.myworld.games;

public class HumanMove implements MoveMethod {
    private TicTacToe game;

    public HumanMove(TicTacToe t) {
        game = t;
    }

    public int move() {

        String move_str;
        int move_int = 0;
        boolean valid_input = false;
        while (!valid_input) {
            System.out.print("Where to ? ");
            move_str = TicTacToe.getUserInput();
            if (Character.isDigit(move_str.toCharArray()[0])) {
                move_int = Integer.parseInt(move_str);
                if ((move_int <= (TicTacToe.N) * (TicTacToe.N)) && move_int >= 1) {
                    valid_input = true;
                    break;
                }
            }

            if (!valid_input) {
                System.out.println("Invalid input");
                continue;
            }
        }
        return move_int;
    }

}
