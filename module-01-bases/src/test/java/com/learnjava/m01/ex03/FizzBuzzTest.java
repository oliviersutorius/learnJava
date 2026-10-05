package com.learnjava.m01.ex03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex03 - FizzBuzz")
class FizzBuzzTest {

    @ParameterizedTest(name = "fizzBuzz({0}) = {1}")
    @CsvSource({
            "1, 1", "2, 2", "3, Fizz", "5, Buzz", "6, Fizz", "10, Buzz",
            "15, FizzBuzz", "30, FizzBuzz", "98, 98", "99, Fizz", "100, Buzz"
    })
    void fizzBuzz(int n, String expected) {
        assertThat(FizzBuzz.fizzBuzz(n)).isEqualTo(expected);
    }

    @Test
    void sequenceOfFive() {
        assertThat(FizzBuzz.sequence(5)).isEqualTo("1 2 Fizz 4 Buzz");
    }

    @Test
    void sequenceOfFifteen() {
        assertThat(FizzBuzz.sequence(15))
                .isEqualTo("1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz");
    }

    @Test
    void sequenceOfOne() {
        assertThat(FizzBuzz.sequence(1)).isEqualTo("1");
    }

    @Test
    @DisplayName("n <= 0 donne une chaîne vide")
    void emptySequence() {
        assertThat(FizzBuzz.sequence(0)).isEmpty();
        assertThat(FizzBuzz.sequence(-4)).isEmpty();
    }
}
