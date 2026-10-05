package com.learnjava.m01.ex01;

public class TemperatureConverter {

    public static double celsiusToFahrenheit(double celsius) {
        // 9.0 / 5 et non 9 / 5 : entre deux int, la division est entière (9 / 5 == 1)
        return celsius * 9.0 / 5 + 32;
    }

    public static double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5.0 / 9;
    }

    public static boolean isFreezing(double celsius) {
        // Une comparaison produit directement un boolean : inutile d'écrire if (...) return true; else return false;
        return celsius <= 0;
    }
}
