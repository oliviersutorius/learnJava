package com.learnjava.m02.ex04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex04 - WordCounter")
class WordCounterTest {

    @Test
    void words() {
        assertThat(WordCounter.words("Bonjour, le monde !")).containsExactly("Bonjour", "le", "monde");
        assertThat(WordCounter.words("un\tdeux\ntrois")).containsExactly("un", "deux", "trois");
        assertThat(WordCounter.words("l'été, peut-être.")).containsExactly("l'été", "peut-être");
    }

    @Test
    @DisplayName("words ne renvoie jamais de chaîne vide")
    void wordsWithoutEmptyStrings() {
        assertThat(WordCounter.words("!Salut")).containsExactly("Salut");
        assertThat(WordCounter.words("...Oui... non ?")).containsExactly("Oui", "non");
        assertThat(WordCounter.words("")).isEmpty();
        assertThat(WordCounter.words("   ")).isEmpty();
        assertThat(WordCounter.words(" , ! ")).isEmpty();
    }

    @ParameterizedTest(name = "\"{0}\" contient {1} mot(s)")
    @CsvSource(value = {
            "Bonjour le monde|3", "  Bonjour,   le monde !  |3", "Java|1", "''|0", "'   '|0", "!Salut|1",
            "Un, deux, trois, soleil !|4"
    }, delimiter = '|', ignoreLeadingAndTrailingWhitespace = false)
    void countWords(String text, int expected) {
        assertThat(WordCounter.countWords(text)).isEqualTo(expected);
    }

    @Test
    void longestWord() {
        assertThat(WordCounter.longestWord("Le langage Java est formidable")).isEqualTo("formidable");
        assertThat(WordCounter.longestWord("un deux six")).isEqualTo("deux");
        assertThat(WordCounter.longestWord("abc def")).isEqualTo("abc");
        assertThat(WordCounter.longestWord("Hello, world!")).isEqualTo("Hello");
        assertThat(WordCounter.longestWord("")).isEmpty();
        assertThat(WordCounter.longestWord(" ?! ")).isEmpty();
    }

    @Test
    void countOccurrences() {
        String text = "Le chat et le chien. LE chat est lent !";
        assertThat(WordCounter.countOccurrences(text, "le")).isEqualTo(3);
        assertThat(WordCounter.countOccurrences(text, "CHAT")).isEqualTo(2);
        assertThat(WordCounter.countOccurrences(text, "lent")).isEqualTo(1);
        assertThat(WordCounter.countOccurrences(text, "oiseau")).isZero();
        assertThat(WordCounter.countOccurrences("", "le")).isZero();
    }

    @Test
    void mostFrequentWord() {
        assertThat(WordCounter.mostFrequentWord("Le chat et le chien. LE chat !")).isEqualTo("le");
        assertThat(WordCounter.mostFrequentWord("Java")).isEqualTo("java");
        assertThat(WordCounter.mostFrequentWord("")).isEmpty();
    }

    @Test
    @DisplayName("En cas d'égalité, le mot le plus fréquent est celui qui apparaît en premier")
    void mostFrequentWordTie() {
        assertThat(WordCounter.mostFrequentWord("chien chat chat chien")).isEqualTo("chien");
        assertThat(WordCounter.mostFrequentWord("a b c")).isEqualTo("a");
    }
}
