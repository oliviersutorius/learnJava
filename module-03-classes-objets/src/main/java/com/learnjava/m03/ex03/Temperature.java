package com.learnjava.m03.ex03;

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
 *
 * TODO : choisis UNE unité de stockage, déclare l'attribut et écris le constructeur private.
 */
public class Temperature {

    /**
     * Exemple : Temperature.ofCelsius(21.5).
     */
    public static Temperature ofCelsius(double celsius) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Exemple : Temperature.ofFahrenheit(212).getCelsius() = 100.0.
     */
    public static Temperature ofFahrenheit(double fahrenheit) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Exemple : Temperature.ofKelvin(0).getCelsius() = -273.15.
     */
    public static Temperature ofKelvin(double kelvin) {
        throw new UnsupportedOperationException("TODO");
    }

    public double getCelsius() {
        throw new UnsupportedOperationException("TODO");
    }

    public double getFahrenheit() {
        throw new UnsupportedOperationException("TODO");
    }

    public double getKelvin() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie une NOUVELLE température, plus chaude de deltaCelsius degrés (plus froide si deltaCelsius est négatif).
     * Cette température-ci ne change pas. Le zéro absolu s'applique aussi au résultat.
     * Exemple : ofCelsius(20).plus(1.5) vaut 21.5 °C, et ofCelsius(20) vaut toujours 20 °C.
     */
    public Temperature plus(double deltaCelsius) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si l'eau gèle à cette température (0 °C ou moins).
     */
    public boolean isFreezing() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si cette température est strictement plus chaude que other.
     */
    public boolean isWarmerThan(Temperature other) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * La température en Celsius, avec une décimale et un point décimal. Exemples : "21.5 °C", "-3.0 °C".
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO");
    }
}
