package com.learnjava.m02.ex01;

/**
 * Exercice 1 — Manipuler des chaînes de caractères.
 * Notions : String et ses méthodes, char, classe Character, StringBuilder, méthodes privées d'aide.
 * Pour toutes les méthodes, le texte reçu n'est jamais null.
 */
public class StringTools {

    /**
     * Renvoie le texte à l'envers.
     * Exemples : reverse("Java") = "avaJ", reverse("") = "".
     */
    public static String reverse(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si le texte est un palindrome, en ignorant la casse et tout ce qui n'est ni une lettre ni un chiffre.
     * Exemples : "Kayak" vrai, "A man, a plan, a canal: Panama" vrai, "Java" faux.
     * Un texte vide (ou sans aucune lettre ni chiffre) est considéré comme un palindrome.
     * Astuce : Character.isLetterOrDigit(c). Écrire une méthode privée d'aide qui « nettoie » le texte est une bonne idée.
     */
    public static boolean isPalindrome(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Compte les voyelles non accentuées (a, e, i, o, u, y), en majuscules comme en minuscules.
     * Exemple : countVowels("Bonjour le MONDE") = 6.
     */
    public static int countVowels(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Met en majuscule la première lettre de chaque mot et le reste en minuscules.
     * Les mots sont séparés par des espaces, qui doivent être conservés tels quels (même s'il y en a plusieurs).
     * Exemples : capitalize("bonJOUR le monde") = "Bonjour Le Monde", capitalize("  a  b ") = "  A  B ".
     */
    public static String capitalize(String text) {
        throw new UnsupportedOperationException("TODO");
    }
}
