package com.learnjava.m01.ex04;

/**
 * Exercice 4 — Formatage de durées.
 * Notions : division entière /, modulo %, String.format, construction conditionnelle d'une String.
 * Pour toutes les méthodes, on suppose que les valeurs reçues sont positives ou nulles.
 */
public class TimeFormatter {

    private static final int SECONDS_PER_MINUTE = 60;
    private static final int SECONDS_PER_HOUR = 3600;

    /**
     * Convertit des heures, minutes et secondes en un nombre total de secondes.
     * Exemple : toSeconds(1, 2, 3) = 3723.
     */
    public static int toSeconds(int hours, int minutes, int seconds) {
        return hours*SECONDS_PER_HOUR + minutes*SECONDS_PER_MINUTE + seconds;
    }

    /**
     * Formate un nombre de secondes au format "HH:MM:SS", chaque partie sur au moins 2 chiffres.
     * Exemples : format(3723) = "01:02:03", format(59) = "00:00:59", format(360000) = "100:00:00".
     * Astuce : String.format("%02d", 7) donne "07".
     * "3599, 00:59:59"
     */
    public static String format(int totalSeconds) {
        int hour = totalSeconds / SECONDS_PER_HOUR;
        int minutes = (totalSeconds - (hour * SECONDS_PER_HOUR)) / SECONDS_PER_MINUTE;
        int seconds = totalSeconds - (hour *SECONDS_PER_HOUR) - (minutes * SECONDS_PER_MINUTE);

        return String.format("%02d",hour)+':'+String.format("%02d",minutes)+':'+String.format("%02d",seconds);
    }

    /**
     * Formate une durée de manière lisible, en omettant les parties nulles.
     * Exemples : humanize(3723) = "1h 2min 3s", humanize(7205) = "2h 5s", humanize(60) = "1min",
     *            humanize(0) = "0s".
     */
    public static String humanize(int totalSeconds) {
        if (totalSeconds == 0) {
            return "0s";
        }

        int hour = totalSeconds / SECONDS_PER_HOUR;
        int minutes = totalSeconds % SECONDS_PER_HOUR / SECONDS_PER_MINUTE;
        int seconds = totalSeconds % SECONDS_PER_MINUTE;

        String valeur = "";

        if (hour > 0) {
            valeur = valeur + hour + 'h';
            if (minutes > 0 || seconds > 0) {
                valeur = valeur + ' ';
            }
        }
        if (minutes > 0) {
            valeur = valeur + minutes + "min";
            if (seconds > 0) {
                valeur = valeur + ' ';
            }
        }
        if (seconds > 0) {
            valeur = valeur + seconds + 's';
        }

        return valeur;
    }
}
