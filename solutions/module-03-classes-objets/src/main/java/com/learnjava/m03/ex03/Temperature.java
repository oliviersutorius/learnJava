package com.learnjava.m03.ex03;

import java.util.Locale;

public class Temperature {

    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double FREEZING_POINT_CELSIUS = 0.0;

    // Une seule unité de stockage : les autres sont calculées à la demande, elles ne peuvent donc pas se contredire
    private final double celsius;

    // private : on ne crée une Temperature qu'avec les méthodes of..., qui disent dans quelle unité est la valeur
    private Temperature(double celsius) {
        this.celsius = Math.max(ABSOLUTE_ZERO_CELSIUS, celsius);
    }

    public static Temperature ofCelsius(double celsius) {
        return new Temperature(celsius);
    }

    public static Temperature ofFahrenheit(double fahrenheit) {
        // 5.0 est un double : la division est décimale sans cast (règle 1.2)
        return new Temperature((fahrenheit - 32) * 5.0 / 9);
    }

    public static Temperature ofKelvin(double kelvin) {
        return new Temperature(kelvin + ABSOLUTE_ZERO_CELSIUS);
    }

    public double getCelsius() {
        return celsius;
    }

    public double getFahrenheit() {
        return celsius * 9.0 / 5 + 32;
    }

    public double getKelvin() {
        return celsius - ABSOLUTE_ZERO_CELSIUS;
    }

    public Temperature plus(double deltaCelsius) {
        // Un nouvel objet : this ne change pas. Le constructeur applique le zéro absolu.
        return new Temperature(celsius + deltaCelsius);
    }

    public boolean isFreezing() {
        return celsius <= FREEZING_POINT_CELSIUS;
    }

    public boolean isWarmerThan(Temperature other) {
        return celsius > other.celsius;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%.1f °C", celsius);
    }
}
