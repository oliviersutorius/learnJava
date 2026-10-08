package com.learnjava.m03.ex03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Ex03 - Temperature")
class TemperatureTest {

    private static final double PRECISION = 1e-9;

    @Test
    @DisplayName("Les attributs sont private final : l'objet est immuable")
    void fieldsArePrivateFinal() {
        assertThat(Temperature.class.getDeclaredFields())
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers())
                        && Modifier.isFinal(field.getModifiers()))
                        .as("l'attribut %s doit être private final", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Il n'y a aucun constructeur public ni aucun setter")
    void noPublicConstructorNorSetter() {
        assertThat(Temperature.class.getConstructors()).as("constructeurs publics").isEmpty();
        assertThat(Temperature.class.getMethods())
                .extracting(Method::getName)
                .noneMatch(name -> name.startsWith("set"));
    }

    @ParameterizedTest(name = "{0} °C = {1} °F = {2} K")
    @CsvSource({"0, 32, 273.15", "100, 212, 373.15", "-40, -40, 233.15", "37, 98.6, 310.15", "21.5, 70.7, 294.65"})
    void conversions(double celsius, double fahrenheit, double kelvin) {
        Temperature fromCelsius = Temperature.ofCelsius(celsius);
        assertThat(fromCelsius.getCelsius()).isCloseTo(celsius, within(PRECISION));
        assertThat(fromCelsius.getFahrenheit()).isCloseTo(fahrenheit, within(PRECISION));
        assertThat(fromCelsius.getKelvin()).isCloseTo(kelvin, within(PRECISION));

        assertThat(Temperature.ofFahrenheit(fahrenheit).getCelsius()).isCloseTo(celsius, within(PRECISION));
        assertThat(Temperature.ofKelvin(kelvin).getCelsius()).isCloseTo(celsius, within(PRECISION));
    }

    @Test
    @DisplayName("Une température sous le zéro absolu est ramenée à 0 K")
    void belowAbsoluteZero() {
        assertThat(Temperature.ofCelsius(-300).getKelvin()).isCloseTo(0, within(PRECISION));
        assertThat(Temperature.ofFahrenheit(-500).getKelvin()).isCloseTo(0, within(PRECISION));
        assertThat(Temperature.ofKelvin(-1).getCelsius()).isCloseTo(-273.15, within(PRECISION));
        assertThat(Temperature.ofKelvin(0).getFahrenheit()).isCloseTo(-459.67, within(PRECISION));
    }

    @Test
    @DisplayName("plus renvoie une nouvelle température et ne modifie pas l'originale")
    void plusReturnsNewObject() {
        Temperature original = Temperature.ofCelsius(20);
        Temperature warmer = original.plus(1.5);
        Temperature colder = original.plus(-25);

        assertThat(warmer.getCelsius()).isCloseTo(21.5, within(PRECISION));
        assertThat(colder.getCelsius()).isCloseTo(-5, within(PRECISION));
        assertThat(original.getCelsius()).isCloseTo(20, within(PRECISION));
        assertThat(warmer).isNotSameAs(original);
    }

    @Test
    @DisplayName("plus respecte aussi le zéro absolu")
    void plusRespectsAbsoluteZero() {
        assertThat(Temperature.ofCelsius(20).plus(-1000).getKelvin()).isCloseTo(0, within(PRECISION));
    }

    @Test
    void isFreezing() {
        assertThat(Temperature.ofCelsius(0).isFreezing()).isTrue();
        assertThat(Temperature.ofCelsius(-10).isFreezing()).isTrue();
        assertThat(Temperature.ofFahrenheit(32).isFreezing()).isTrue();
        assertThat(Temperature.ofCelsius(0.1).isFreezing()).isFalse();
        assertThat(Temperature.ofFahrenheit(50).isFreezing()).isFalse();
    }

    @Test
    void isWarmerThan() {
        Temperature mild = Temperature.ofCelsius(15);
        assertThat(mild.isWarmerThan(Temperature.ofCelsius(10))).isTrue();
        assertThat(mild.isWarmerThan(Temperature.ofCelsius(20))).isFalse();
        assertThat(mild.isWarmerThan(Temperature.ofCelsius(15))).as("égalité").isFalse();
        assertThat(Temperature.ofFahrenheit(212).isWarmerThan(Temperature.ofKelvin(373))).isTrue();
    }

    @Test
    @DisplayName("toString affiche les degrés Celsius avec une décimale et un point")
    void toStringFormat() {
        Locale previous = Locale.getDefault();
        try {
            Locale.setDefault(Locale.FRANCE);
            assertThat(Temperature.ofCelsius(21.5)).hasToString("21.5 °C");
            assertThat(Temperature.ofCelsius(-3)).hasToString("-3.0 °C");
            assertThat(Temperature.ofFahrenheit(212)).hasToString("100.0 °C");
        } finally {
            Locale.setDefault(previous);
        }
    }
}
