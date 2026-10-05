package com.learnjava.m01.ex05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex05 - PrimeNumbers")
class PrimeNumbersTest {

    @ParameterizedTest(name = "{0} est premier")
    @ValueSource(ints = {2, 3, 5, 7, 11, 13, 97, 7919, 2_147_483_647})
    void primes(int n) {
        assertThat(PrimeNumbers.isPrime(n)).isTrue();
    }

    @ParameterizedTest(name = "{0} n''est pas premier")
    @ValueSource(ints = {-7, 0, 1, 4, 9, 15, 25, 100, 7917})
    void notPrimes(int n) {
        assertThat(PrimeNumbers.isPrime(n)).isFalse();
    }

    @ParameterizedTest(name = "{1} nombres premiers jusqu''à {0}")
    @CsvSource({"1, 0", "2, 1", "10, 4", "100, 25", "1000, 168"})
    void countPrimesUpTo(int limit, int expected) {
        assertThat(PrimeNumbers.countPrimesUpTo(limit)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "le {0}e nombre premier est {1}")
    @CsvSource({"1, 2", "2, 3", "4, 7", "10, 29", "100, 541", "1000, 7919"})
    void nthPrime(int n, int expected) {
        assertThat(PrimeNumbers.nthPrime(n)).isEqualTo(expected);
    }

    @Test
    @Timeout(2)
    @DisplayName("Bonus performance : tester un grand nombre premier doit être rapide")
    void isPrimeIsFast() {
        // Échoue si on teste tous les diviseurs jusqu'à n au lieu de s'arrêter à √n
        for (int i = 0; i < 20; i++) {
            assertThat(PrimeNumbers.isPrime(2_147_483_647)).isTrue();
        }
    }
}
