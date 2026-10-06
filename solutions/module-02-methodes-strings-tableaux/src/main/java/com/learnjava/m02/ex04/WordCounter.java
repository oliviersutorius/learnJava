package com.learnjava.m02.ex04;

public class WordCounter {

    private static final String SEPARATORS = "[\\s,.;:!?]+";

    public static String[] words(String text) {
        // split peut produire une chaîne vide en tête (texte qui commence par un séparateur) ou "" pour un texte vide :
        // on compte d'abord les vrais mots, car un tableau a une taille fixe qu'il faut connaître à sa création
        String[] tokens = text.split(SEPARATORS);
        int count = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                count++;
            }
        }

        String[] words = new String[count];
        int index = 0;
        for (String token : tokens) {
            if (!token.isEmpty()) {
                words[index] = token;
                index++;
            }
        }
        return words;
    }

    public static int countWords(String text) {
        return words(text).length;
    }

    public static String longestWord(String text) {
        String longest = "";
        for (String word : words(text)) {
            // > strict : en cas d'égalité, on garde le premier
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    public static int countOccurrences(String text, String word) {
        return countOccurrences(words(text), word);
    }

    // Surcharge privée : évite de redécouper le texte à chaque appel depuis mostFrequentWord
    private static int countOccurrences(String[] words, String word) {
        int count = 0;
        for (String candidate : words) {
            if (candidate.equalsIgnoreCase(word)) {
                count++;
            }
        }
        return count;
    }

    public static String mostFrequentWord(String text) {
        String[] words = words(text);
        String mostFrequent = "";
        int bestCount = 0;
        for (String word : words) {
            int count = countOccurrences(words, word);
            if (count > bestCount) {
                bestCount = count;
                mostFrequent = word;
            }
        }
        // Complexité O(n²) : acceptable ici ; une Map (module 5) permettra de faire mieux
        return mostFrequent.toLowerCase();
    }
}
