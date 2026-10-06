package com.learnjava.m02.ex03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex03 - CaesarCipher")
class CaesarCipherTest {

    @ParameterizedTest(name = "encrypt(\"{0}\", {1}) = \"{2}\"")
    @CsvSource(value = {
            "abc|1|bcd", "xyz|3|abc", "ABC|1|BCD", "Zoo !|1|App !", "abc|-1|zab", "abc|27|bcd",
            "abc|-27|zab", "abc|26|abc", "abc|0|abc", "Hello, World 2026|13|Uryyb, Jbeyq 2026",
            "Café|1|Dbgé"
    }, delimiter = '|')
    void encrypt(String text, int shift, String expected) {
        assertThat(CaesarCipher.encrypt(text, shift)).isEqualTo(expected);
    }

    @Test
    @DisplayName("encrypt sans décalage utilise le décalage de César (3)")
    void encryptWithDefaultShift() {
        assertThat(CaesarCipher.encrypt("Ave Cesar")).isEqualTo("Dyh Fhvdu");
    }

    @ParameterizedTest(name = "decrypt(\"{0}\", {1}) = \"{2}\"")
    @CsvSource(value = {"bcd|1|abc", "abc|3|xyz", "Dyh Fhvdu|3|Ave Cesar", "zab|-1|abc"}, delimiter = '|')
    void decrypt(String text, int shift, String expected) {
        assertThat(CaesarCipher.decrypt(text, shift)).isEqualTo(expected);
    }

    @Test
    @DisplayName("decrypt(encrypt(t, s), s) redonne le texte d'origine")
    void roundTrip() {
        String text = "Le Java, c'est Fantastique !";
        for (int shift = -30; shift <= 30; shift++) {
            assertThat(CaesarCipher.decrypt(CaesarCipher.encrypt(text, shift), shift)).isEqualTo(text);
        }
    }

    @Test
    void allShifts() {
        String[] candidates = CaesarCipher.allShifts("Dyh Fhvdu");

        assertThat(candidates).hasSize(26);
        assertThat(candidates[0]).isEqualTo("Dyh Fhvdu");
        assertThat(candidates[1]).isEqualTo("Cxg Eguct");
        assertThat(candidates[3]).isEqualTo("Ave Cesar");
        assertThat(candidates[25]).isEqualTo("Ezi Giwev");
    }
}
