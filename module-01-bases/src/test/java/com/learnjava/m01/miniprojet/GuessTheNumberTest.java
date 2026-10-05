package com.learnjava.m01.miniprojet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mini-projet - GuessTheNumber")
class GuessTheNumberTest {

    @Test
    void hintTooLow() {
        assertThat(GuessTheNumber.hint(50, 10)).isEqualTo(GuessTheNumber.TOO_LOW);
        assertThat(GuessTheNumber.hint(50, 49)).isEqualTo(GuessTheNumber.TOO_LOW);
    }

    @Test
    void hintTooHigh() {
        assertThat(GuessTheNumber.hint(50, 90)).isEqualTo(GuessTheNumber.TOO_HIGH);
        assertThat(GuessTheNumber.hint(50, 51)).isEqualTo(GuessTheNumber.TOO_HIGH);
    }

    @Test
    void hintWin() {
        assertThat(GuessTheNumber.hint(42, 42)).isEqualTo(GuessTheNumber.WIN);
    }

    @Test
    void isInRange() {
        assertThat(GuessTheNumber.isInRange(GuessTheNumber.MIN)).isTrue();
        assertThat(GuessTheNumber.isInRange(GuessTheNumber.MAX)).isTrue();
        assertThat(GuessTheNumber.isInRange(50)).isTrue();
        assertThat(GuessTheNumber.isInRange(GuessTheNumber.MIN - 1)).isFalse();
        assertThat(GuessTheNumber.isInRange(GuessTheNumber.MAX + 1)).isFalse();
    }
}
