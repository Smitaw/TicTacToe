package com.myworld.games;

import java.util.ArrayList;
import java.util.List;

public class Formatter {

    static final List<String> formattedMessages = new ArrayList<>(TicTacToe.N);

    static {
        for (int i = 0; i < TicTacToe.N; i++) {
            formattedMessages.add(i, displayMessage(i));
        }
    }

    public static String getPosDescription(int pos) {
        String str = "";
        if (pos == 5) {
            str = "center";
            return str;
        }

        if ((pos - 1) / 3 == 0) {
            str += "upper ";
        } else if ((pos - 1) / 3 == 1) {
            str += "middle ";
        } else
            str += "lower ";

        if ((pos - 1) % 3 == 0)
            str += "left";
        else if ((pos - 1) % 3 == 1)
            str += "middle";
        else
            str += "right";

        return str;
    }

    protected static String getMessageRow(int row) {
        return formattedMessages.get(row);
    }

    protected static String displayMessage(int row) {
        String s = new String();
        for (int i = 0; i < 3; i++) {
            s += row * 3 + i + 1;

            if (i == 3 - 1) {
                s += "\n";
            } else {
                s += TicTacToe.SLASH;
            }
        }
        return s;
    }
}
