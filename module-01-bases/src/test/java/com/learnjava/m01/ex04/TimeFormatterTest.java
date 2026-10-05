package com.learnjava.m01.ex04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex04 - TimeFormatter")
class TimeFormatterTest {

    @ParameterizedTest(name = "{0}h {1}min {2}s = {3}s")
    @CsvSource({"0, 0, 0, 0", "1, 2, 3, 3723", "0, 1, 0, 60", "2, 0, 5, 7205", "24, 0, 0, 86400"})
    void toSeconds(int hours, int minutes, int seconds, int expected) {
        assertThat(TimeFormatter.toSeconds(hours, minutes, seconds)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "format({0}) = {1}")
    @CsvSource({
            "0, 00:00:00", "59, 00:00:59", "60, 00:01:00", "3599, 00:59:59",
            "3723, 01:02:03", "86399, 23:59:59", "360000, 100:00:00"
    })
    void format(int totalSeconds, String expected) {
        assertThat(TimeFormatter.format(totalSeconds)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "humanize({0}) = \"{1}\"")
    @CsvSource({
            "0, 0s", "5, 5s", "60, 1min", "61, 1min 1s", "3600, 1h", "3723, 1h 2min 3s",
            "7205, 2h 5s", "7260, 2h 1min", "90061, 25h 1min 1s"
    })
    void humanize(int totalSeconds, String expected) {
        assertThat(TimeFormatter.humanize(totalSeconds)).isEqualTo(expected);
    }
}
