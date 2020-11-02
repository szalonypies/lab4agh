package com.luxoft.lab4.util;

public class Nth {
    public static String of(int i) {
        int j = i % 100;
        if (j >= 10 && j <= 20) {
            return i + "th";
        }
        switch (j % 10) {
            case 1:
                return i+"st";
            case 2:
                return i+"nd";
            case 3:
                return i+"rd";
        }
        return i+"th";
    }
}
