package com.myworld.games;

public class Formatter {
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
}
