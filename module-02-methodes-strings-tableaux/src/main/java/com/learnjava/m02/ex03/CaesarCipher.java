package com.learnjava.m02.ex03;

/**
 * Exercice 3 — Le chiffre de César.
 * Notions : arithmétique sur les char, cast int -> char, modulo avec des nombres négatifs, surcharge de méthodes,
 * réutilisation d'une méthode par une autre, tableau de String.
 *
 * Chaque lettre non accentuée (a-z, A-Z) est décalée de « shift » positions dans l'alphabet, en revenant au début
 * après z (décalage de 3 : a -> d, x -> a). La casse est conservée ; tous les autres caractères (espaces,
 * ponctuation, chiffres, lettres accentuées) restent inchangés.
 */
public class CaesarCipher {

    /**
     * Chiffre le texte avec un décalage quelconque : il peut être négatif (décalage vers la gauche)
     * ou supérieur à 26 (27 revient à 1).
     * Exemples : encrypt("abc", 1) = "bcd", encrypt("Zoo !", 1) = "App !", encrypt("abc", -1) = "zab".
     */
    public static String encrypt(String text, int shift) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Surcharge : chiffre avec le décalage historique de Jules César, 3.
     * Exemple : encrypt("Ave Cesar") = "Dyh Fhvdu".
     * Astuce : une ligne suffit.
     */
    public static String encrypt(String text) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Déchiffre un texte chiffré avec le décalage donné : decrypt(encrypt(t, s), s) redonne t.
     * Astuce : une ligne suffit.
     */
    public static String decrypt(String text, int shift) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Attaque par force brute : renvoie un tableau de 26 chaînes où l'élément d'indice i est
     * le texte déchiffré avec le décalage i (l'élément 0 est donc le texte inchangé).
     */
    public static String[] allShifts(String text) {
        throw new UnsupportedOperationException("TODO");
    }
}
