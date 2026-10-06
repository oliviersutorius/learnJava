package com.learnjava.m02.ex01;

import java.util.Locale;

/**
 * Exercice 1 — Manipuler des chaînes de caractères.
 * Notions : String et ses méthodes, char, classe Character, StringBuilder, méthodes privées d'aide.
 * Pour toutes les méthodes, le texte reçu n'est jamais null.
 */
public class StringTools {

    private static final String VOWELS = "aeiouy";

    /**
     * Renvoie le texte à l'envers.
     * Exemples : reverse("Java") = "avaJ", reverse("") = "".
     */
    public static String reverse(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    /**
     * Indique si le texte est un palindrome, en ignorant la casse et tout ce qui n'est ni une lettre ni un chiffre.
     * Exemples : "Kayak" vrai, "A man, a plan, a canal: Panama" vrai, "Java" faux.
     * Un texte vide (ou sans aucune lettre ni chiffre) est considéré comme un palindrome.
     * Astuce : Character.isLetterOrDigit(c). Écrire une méthode privée d'aide qui « nettoie » le texte est une bonne idée.
     */
    public static boolean isPalindrome(String text) {
        String cleanedText = cleanText(text);

        return cleanedText.equals(reverse(cleanedText));
    }

    /**
     * Renvoie le texte d'entrée nettoyé des caractères non lettre ou chiffre
     */
    private static String cleanText(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }

    /**
     * Compte les voyelles non accentuées (a, e, i, o, u, y), en majuscules comme en minuscules.
     * Exemple : countVowels("Bonjour le MONDE") = 6.
     */
    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase(Locale.ROOT);

        for (int i = 0; i < lowerText.length(); i++) {
            if (VOWELS.indexOf(lowerText.charAt(i)) != -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * Met en majuscule la première lettre de chaque mot et le reste en minuscules.
     * Les mots sont séparés par des espaces, qui doivent être conservés tels quels (même s'il y en a plusieurs).
     * Exemples : capitalize("bonJOUR le monde") = "Bonjour Le Monde", capitalize("  a  b ") = "  A  B ".
     */
    public static String capitalize(String text) {
        String[] words = text.split(" ", -1);

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                words[i] = words[i].substring(0, 1).toUpperCase(Locale.ROOT)
                        + words[i].substring(1).toLowerCase(Locale.ROOT);
            }
        }

        return String.join(" ", words);
    }
}
