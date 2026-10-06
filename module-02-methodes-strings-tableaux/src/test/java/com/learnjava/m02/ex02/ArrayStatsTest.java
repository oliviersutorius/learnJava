package com.learnjava.m02.ex02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Ex02 - ArrayStats")
class ArrayStatsTest {

    @Test
    void sum() {
        assertThat(ArrayStats.sum(new int[]{1, 2, 3, 4})).isEqualTo(10);
        assertThat(ArrayStats.sum(new int[]{-5, 5})).isZero();
        assertThat(ArrayStats.sum(new int[]{})).isZero();
    }

    @Test
    @DisplayName("La somme ne déborde pas au-delà de Integer.MAX_VALUE")
    void sumDoesNotOverflow() {
        assertThat(ArrayStats.sum(new int[]{Integer.MAX_VALUE, 1})).isEqualTo(2_147_483_648L);
        assertThat(ArrayStats.sum(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE}))
                .isEqualTo(6_442_450_941L);
    }

    @Test
    void min() {
        assertThat(ArrayStats.min(new int[]{3, 1, 2})).isEqualTo(1);
        assertThat(ArrayStats.min(new int[]{-3, -10, 4})).isEqualTo(-10);
        assertThat(ArrayStats.min(new int[]{42})).isEqualTo(42);
        assertThat(ArrayStats.min(new int[]{5, 5, 5})).isEqualTo(5);
    }

    @Test
    void max() {
        assertThat(ArrayStats.max(new int[]{3, 1, 2})).isEqualTo(3);
        assertThat(ArrayStats.max(new int[]{-3, -10, -4})).isEqualTo(-3);
        assertThat(ArrayStats.max(new int[]{42})).isEqualTo(42);
    }

    @Test
    @DisplayName("La moyenne garde ses décimales")
    void average() {
        assertThat(ArrayStats.average(new int[]{1, 2})).isCloseTo(1.5, within(1e-9));
        assertThat(ArrayStats.average(new int[]{10, 20, 30})).isCloseTo(20.0, within(1e-9));
        assertThat(ArrayStats.average(new int[]{1, 2, 2})).isCloseTo(5.0 / 3, within(1e-9));
        assertThat(ArrayStats.average(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}))
                .isCloseTo(2_147_483_647.0, within(1e-9));
    }

    @Test
    void averageOfEmptyArray() {
        assertThat(ArrayStats.average(new int[]{})).isZero();
    }

    @Test
    void median() {
        assertThat(ArrayStats.median(new int[]{3, 1, 2})).isCloseTo(2.0, within(1e-9));
        assertThat(ArrayStats.median(new int[]{4, 1, 3, 2})).isCloseTo(2.5, within(1e-9));
        assertThat(ArrayStats.median(new int[]{7})).isCloseTo(7.0, within(1e-9));
        assertThat(ArrayStats.median(new int[]{10, 1, 1000, 2, 3})).isCloseTo(3.0, within(1e-9));
        assertThat(ArrayStats.median(new int[]{})).isZero();
    }

    @Test
    @DisplayName("La médiane de deux très grands nombres ne déborde pas")
    void medianDoesNotOverflow() {
        assertThat(ArrayStats.median(new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE}))
                .isCloseTo(2_147_483_647.0, within(1e-9));
    }

    @Test
    @DisplayName("median ne modifie pas le tableau reçu")
    void medianDoesNotModifyInput() {
        int[] values = {4, 1, 3, 2};
        ArrayStats.median(values);
        assertThat(values).containsExactly(4, 1, 3, 2);
    }

    @Test
    void countAbove() {
        assertThat(ArrayStats.countAbove(new int[]{5, 10, 15}, 9.5)).isEqualTo(2);
        assertThat(ArrayStats.countAbove(new int[]{5, 10, 15}, 10)).isEqualTo(1);
        assertThat(ArrayStats.countAbove(new int[]{5, 10, 15}, 100)).isZero();
        assertThat(ArrayStats.countAbove(new int[]{}, 0)).isZero();
    }
}
