package com.learnjava.m01.ex02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex02 - LeapYear")
class LeapYearTest {

    @ParameterizedTest(name = "{0} est bissextile")
    @ValueSource(ints = {2024, 2000, 1600, 2400, 4})
    void leapYears(int year) {
        assertThat(LeapYear.isLeapYear(year)).isTrue();
    }

    @ParameterizedTest(name = "{0} n''est pas bissextile")
    @ValueSource(ints = {2023, 1900, 2100, 1800, 2025})
    void nonLeapYears(int year) {
        assertThat(LeapYear.isLeapYear(year)).isFalse();
    }

    @ParameterizedTest(name = "mois {0} de {1} : {2} jours")
    @CsvSource({
            "1, 2023, 31", "2, 2023, 28", "2, 2024, 29", "2, 1900, 28", "2, 2000, 29",
            "3, 2023, 31", "4, 2023, 30", "6, 2023, 30", "7, 2023, 31", "8, 2023, 31",
            "9, 2023, 30", "11, 2023, 30", "12, 2023, 31"
    })
    void daysInMonth(int month, int year, int expected) {
        assertThat(LeapYear.daysInMonth(month, year)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Un mois hors de 1..12 renvoie -1")
    void invalidMonth() {
        assertThat(LeapYear.daysInMonth(0, 2024)).isEqualTo(-1);
        assertThat(LeapYear.daysInMonth(13, 2024)).isEqualTo(-1);
        assertThat(LeapYear.daysInMonth(-3, 2024)).isEqualTo(-1);
    }

    @ParameterizedTest(name = "{0}/{1}/{2} valide ? {3}")
    @CsvSource({
            "29, 2, 2024, true", "29, 2, 2023, false", "31, 4, 2024, false", "30, 4, 2024, true",
            "0, 1, 2024, false", "31, 12, 2024, true", "32, 1, 2024, false", "15, 13, 2024, false",
            "1, 1, 2024, true", "-1, 3, 2024, false"
    })
    void isValidDate(int day, int month, int year, boolean expected) {
        assertThat(LeapYear.isValidDate(day, month, year)).isEqualTo(expected);
    }
}
