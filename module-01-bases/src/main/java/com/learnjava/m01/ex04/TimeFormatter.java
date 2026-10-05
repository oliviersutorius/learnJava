package com.learnjava.m01.ex04;

/**
 * Exercice 4 — Formatage de durées.
 * Notions : division entière /, modulo %, String.format, construction conditionnelle d'une String.
 * Pour toutes les méthodes, on suppose que les valeurs reçues sont positives ou nulles.
 */
public class TimeFormatter {

    /**
     * Convertit des heures, minutes et secondes en un nombre total de secondes.
     * Exemple : toSeconds(1, 2, 3) = 3723.
     */
    public static int toSeconds(int hours, int minutes, int seconds) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Formate un nombre de secondes au format "HH:MM:SS", chaque partie sur au moins 2 chiffres.
     * Exemples : format(3723) = "01:02:03", format(59) = "00:00:59", format(360000) = "100:00:00".
     * Astuce : String.format("%02d", 7) donne "07".
     */
    public static String format(int totalSeconds) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Formate une durée de manière lisible, en omettant les parties nulles.
     * Exemples : humanize(3723) = "1h 2min 3s", humanize(7205) = "2h 5s", humanize(60) = "1min",
     *            humanize(0) = "0s".
     */
    public static String humanize(int totalSeconds) {
        throw new UnsupportedOperationException("TODO");
    }
}
