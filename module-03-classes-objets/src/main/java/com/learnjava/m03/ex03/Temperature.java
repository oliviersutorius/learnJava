package com.learnjava.m03.ex03;

import java.util.Locale;

/**
 * Exercice 3 — Une température immuable.
 * Notions : objet immuable (attributs private final, pas de setter), constructeur private,
 * méthodes de fabrique statiques, méthode qui renvoie un nouvel objet.
 *
 * Une Temperature ne change jamais après sa création. On la crée uniquement avec ofCelsius, ofFahrenheit ou ofKelvin :
 * la classe ne doit avoir AUCUN constructeur public (les tests le vérifient).
 * Une température ne peut pas descendre sous le zéro absolu (-273.15 °C, soit 0 K) : une valeur plus basse est
 * ramenée au zéro absolu.
 *
 * Conversions : °F = °C × 9/5 + 32 ; K = °C + 273.15.
 */
public final class Temperature {

    private static final double ABSOLUTE_ZERO_CELSIUS = -273.15;
    private static final double FREEZING_POINT_CELSIUS = 0.0;

    private final double celsius;

    private Temperature(double celsius) {
        this.celsius = Math.max(ABSOLUTE_ZERO_CELSIUS, celsius);
    }

    /**
     * Exemple : Temperature.ofCelsius(21.5).
     */
    public static Temperature ofCelsius(double celsius) {
        return new Temperature(celsius);
    }

    /**
     * Exemple : Temperature.ofFahrenheit(212).getCelsius() = 100.0.
     */
    public static Temperature ofFahrenheit(double fahrenheit) {
        return new Temperature((fahrenheit - 32) * 5.0 / 9);
    }

    /**
     * Exemple : Temperature.ofKelvin(0).getCelsius() = -273.15.
     */
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

    /**
     * Renvoie une NOUVELLE température, plus chaude de deltaCelsius degrés (plus froide si deltaCelsius est négatif).
     * Cette température-ci ne change pas. Le zéro absolu s'applique aussi au résultat.
     * Exemple : ofCelsius(20).plus(1.5) vaut 21.5 °C, et ofCelsius(20) vaut toujours 20 °C.
     */
    public Temperature plus(double deltaCelsius) {
        return new Temperature(celsius + deltaCelsius);
    }

    /**
     * Indique si l'eau gèle à cette température (0 °C ou moins).
     */
    public boolean isFreezing() {
        return celsius <= FREEZING_POINT_CELSIUS;
    }

    /**
     * Indique si cette température est strictement plus chaude que other.
     */
    public boolean isWarmerThan(Temperature other) {
        return this.celsius > other.celsius;
    }

    /**
     * La température en Celsius, avec une décimale et un point décimal. Exemples : "21.5 °C", "-3.0 °C".
     */
    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%.1f °C", celsius);
    }
}
