package com.learnjava.m02.ex01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex01 - StringTools")
class StringToolsTest {

    @ParameterizedTest(name = "reverse(\"{0}\") = \"{1}\"")
    @CsvSource(value = {"Java|avaJ", "a|a", "ab|ba", "Bonjour le monde|ednom el ruojnoB", "12345|54321"}, delimiter = '|')
    void reverse(String text, String expected) {
        assertThat(StringTools.reverse(text)).isEqualTo(expected);
    }

    @Test
    void reverseEmpty() {
        assertThat(StringTools.reverse("")).isEmpty();
    }

    @ParameterizedTest(name = "\"{0}\" est un palindrome")
    @ValueSource(strings = {"Kayak", "radar", "A man, a plan, a canal: Panama", "Engage le jeu que je le gagne",
            "12321", "", "!!!", "x"})
    void palindromes(String text) {
        assertThat(StringTools.isPalindrome(text)).isTrue();
    }

    @ParameterizedTest(name = "\"{0}\" n''est pas un palindrome")
    @ValueSource(strings = {"Java", "ab", "Bonjour", "12345", "palindrome"})
    void notPalindromes(String text) {
        assertThat(StringTools.isPalindrome(text)).isFalse();
    }

    @ParameterizedTest(name = "\"{0}\" contient {1} voyelle(s)")
    @CsvSource(value = {"Bonjour le MONDE|6", "rythme|2", "AEIOUY|6", "bcdfg|0", "''|0", "Écoute|3"}, delimiter = '|',
            ignoreLeadingAndTrailingWhitespace = false)
    void countVowels(String text, int expected) {
        assertThat(StringTools.countVowels(text)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "capitalize(\"{0}\") = \"{1}\"")
    @CsvSource(value = {"bonJOUR le monde|Bonjour Le Monde", "java|Java", "JAVA|Java", "a b c|A B C",
            "  a  b |  A  B ", "l'été est là|L'été Est Là"}, delimiter = '|', ignoreLeadingAndTrailingWhitespace = false)
    void capitalize(String text, String expected) {
        assertThat(StringTools.capitalize(text)).isEqualTo(expected);
    }

    @Test
    void capitalizeEmpty() {
        assertThat(StringTools.capitalize("")).isEmpty();
    }
}
