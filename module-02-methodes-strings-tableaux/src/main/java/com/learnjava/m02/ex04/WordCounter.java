package com.learnjava.m02.ex04;

/**
 * Exercice 4 — Analyse de texte.
 * Notions : split et expression régulière simple, tableaux de String, remplir un tableau de taille calculée,
 * equalsIgnoreCase, boucles imbriquées.
 *
 * Un « mot » est une suite de caractères séparée des autres par des espaces (un ou plusieurs, y compris tabulations
 * et retours à la ligne) et/ou par la ponctuation , . ; : ! ?
 * L'apostrophe et le tiret font partie du mot : « l'été » et « peut-être » sont un seul mot chacun.
 * Le texte reçu n'est jamais null.
 */
public class WordCounter {

    /**
     * Expression régulière qui reconnaît un ou plusieurs séparateurs consécutifs, à utiliser avec text.split(...).
     */
    private static final String SEPARATORS = "[\\s,.;:!?]+";

    /**
     * Renvoie les mots du texte, dans l'ordre, sans aucune chaîne vide.
     * Exemples : words("Bonjour, le monde !") = {"Bonjour", "le", "monde"}, words("   ") = {} (tableau vide).
     * Piège : regarde ce que renvoie "!Salut".split(SEPARATORS), ou "".split(SEPARATORS).
     */
    public static String[] words(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Compte les mots du texte. Exemple : countWords("  Bonjour,   le monde !  ") = 3.
     */
    public static int countWords(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le mot le plus long ; en cas d'égalité, le premier rencontré. Renvoie "" s'il n'y a aucun mot.
     */
    public static String longestWord(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Compte combien de fois le mot apparaît dans le texte, sans tenir compte de la casse.
     * Seuls les mots entiers comptent : « le » n'apparaît pas dans « lent ».
     */
    public static int countOccurrences(String text, String word) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le mot le plus fréquent, en minuscules. En cas d'égalité, celui qui apparaît en premier dans le texte.
     * Renvoie "" s'il n'y a aucun mot.
     * Exemple : mostFrequentWord("Le chat et le chien. LE chat !") = "le".
     */
    public static String mostFrequentWord(String text) {
        throw new UnsupportedOperationException("TODO");
    }
}
