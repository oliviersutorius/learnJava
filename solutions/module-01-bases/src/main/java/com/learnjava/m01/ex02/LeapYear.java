package com.learnjava.m01.ex02;

public class LeapYear {

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static int daysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return isLeapYear(year) ? 29 : 28;
            case 4, 6, 9, 11:
                return 30;
            case 1, 3, 5, 7, 8, 10, 12:
                return 31;
            default:
                return -1;
        }
    }

    public static boolean isValidDate(int day, int month, int year) {
        int maxDay = daysInMonth(month, year);
        // Si le mois est invalide, maxDay vaut -1 et la condition day <= maxDay est forcément fausse
        return day >= 1 && day <= maxDay;
    }
}
