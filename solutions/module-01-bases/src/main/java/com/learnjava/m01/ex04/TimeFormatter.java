package com.learnjava.m01.ex04;

public class TimeFormatter {

    // Constantes : static final + nom en MAJUSCULES. Évite les « nombres magiques » dans le code.
    private static final int SECONDS_PER_MINUTE = 60;
    private static final int SECONDS_PER_HOUR = 3600;

    public static int toSeconds(int hours, int minutes, int seconds) {
        return hours * SECONDS_PER_HOUR + minutes * SECONDS_PER_MINUTE + seconds;
    }

    public static String format(int totalSeconds) {
        int hours = totalSeconds / SECONDS_PER_HOUR;
        int minutes = totalSeconds % SECONDS_PER_HOUR / SECONDS_PER_MINUTE;
        int seconds = totalSeconds % SECONDS_PER_MINUTE;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String humanize(int totalSeconds) {
        if (totalSeconds == 0) {
            return "0s";
        }
        int hours = totalSeconds / SECONDS_PER_HOUR;
        int minutes = totalSeconds % SECONDS_PER_HOUR / SECONDS_PER_MINUTE;
        int seconds = totalSeconds % SECONDS_PER_MINUTE;

        String result = "";
        if (hours > 0) {
            result += hours + "h ";
        }
        if (minutes > 0) {
            result += minutes + "min ";
        }
        if (seconds > 0) {
            result += seconds + "s";
        }
        // strip() retire l'espace final éventuel (ex. "1h " -> "1h")
        return result.strip();
    }
}
