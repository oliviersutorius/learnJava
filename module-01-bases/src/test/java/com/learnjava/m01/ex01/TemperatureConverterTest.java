package com.learnjava.m01.ex01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Ex01 - TemperatureConverter")
class TemperatureConverterTest {

    @ParameterizedTest(name = "{0} °C = {1} °F")
    @CsvSource({"0, 32", "100, 212", "-40, -40", "37, 98.6", "21.5, 70.7"})
    void celsiusToFahrenheit(double celsius, double expectedFahrenheit) {
        assertThat(TemperatureConverter.celsiusToFahrenheit(celsius)).isCloseTo(expectedFahrenheit, within(0.001));
    }

    @ParameterizedTest(name = "{0} °F = {1} °C")
    @CsvSource({"32, 0", "212, 100", "-40, -40", "50, 10", "0, -17.7778"})
    void fahrenheitToCelsius(double fahrenheit, double expectedCelsius) {
        assertThat(TemperatureConverter.fahrenheitToCelsius(fahrenheit)).isCloseTo(expectedCelsius, within(0.001));
    }

    @Test
    @DisplayName("Un aller-retour Celsius -> Fahrenheit -> Celsius redonne la valeur de départ")
    void roundTrip() {
        double celsius = 18.3;
        double back = TemperatureConverter.fahrenheitToCelsius(TemperatureConverter.celsiusToFahrenheit(celsius));
        assertThat(back).isCloseTo(celsius, within(0.001));
    }

    @Test
    void isFreezing() {
        assertThat(TemperatureConverter.isFreezing(-5)).isTrue();
        assertThat(TemperatureConverter.isFreezing(0)).isTrue();
        assertThat(TemperatureConverter.isFreezing(0.1)).isFalse();
        assertThat(TemperatureConverter.isFreezing(25)).isFalse();
    }
}
