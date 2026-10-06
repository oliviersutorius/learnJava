package com.learnjava.m01.ex02;

/**
 * Exercice 2 — Années bissextiles et calendrier.
 * Notions : opérateur modulo %, opérateurs logiques && || !, if / else if / else, switch.
 */
public class LeapYear {

    /**
     * Une année est bissextile si elle est divisible par 4,
     * SAUF si elle est divisible par 100, À MOINS qu'elle ne soit aussi divisible par 400.
     * Exemples : 2024 oui, 1900 non, 2000 oui, 2023 non.
     */
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0) {
             return year % 400 == 0;
        }

        return year % 4 == 0;
    }

    /**
     * Renvoie le nombre de jours du mois (1 = janvier, 12 = décembre) pour l'année donnée.
     * Février compte 29 jours les années bissextiles.
     * Renvoie -1 si le mois n'est pas compris entre 1 et 12.
     */
    public static int daysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 1,3,5,7,8,10,12:
                return 31;
            case 4,6,9,11:
                return 30;
        }
        return -1;
    }

    /**
     * Indique si la date jour/mois/année existe dans le calendrier.
     * Exemples : 29/02/2024 valide, 29/02/2023 invalide, 31/04/2024 invalide, 0/01/2024 invalide.
     * Astuce : réutilise daysInMonth.
     */
    public static boolean isValidDate(int day, int month, int year) {
        int maxDay = daysInMonth(month, year);
        return day > 0 && day <= maxDay;
    }
}
