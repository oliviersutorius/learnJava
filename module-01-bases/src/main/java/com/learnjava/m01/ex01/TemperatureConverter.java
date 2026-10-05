package com.learnjava.m01.ex01;

/**
 * Exercice 1 — Conversion de températures.
 * Notions : type double, opérateurs arithmétiques, priorité des opérateurs, boolean.
 */
public class TemperatureConverter {

    /**
     * Convertit une température de degrés Celsius en degrés Fahrenheit.
     * Formule : F = C × 9/5 + 32
     */
    public static double celsiusToFahrenheit(double celsius) {
        double farenheit = celsius * ((double) 9 /5) + 32;

        return farenheit;
    }

    /**
     * Convertit une température de degrés Fahrenheit en degrés Celsius.
     * Formule : C = (F − 32) × 5/9
     */
    public static double fahrenheitToCelsius(double fahrenheit) {
        double celsius = (fahrenheit - 32) * (double)5/9;

        return celsius;
    }

    /**
     * Indique si l'eau gèle à cette température (en Celsius), c'est-à-dire si elle est inférieure ou égale à 0.
     */
    public static boolean isFreezing(double celsius) {
        return celsius <= 0;
    }
}
