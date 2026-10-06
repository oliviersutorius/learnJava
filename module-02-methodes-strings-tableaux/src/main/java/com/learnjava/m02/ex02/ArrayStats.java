package com.learnjava.m02.ex02;

/**
 * Exercice 2 — Statistiques sur un tableau d'entiers.
 * Notions : tableaux, boucle for-each, long et débordement, division décimale, Arrays.copyOf / Arrays.sort,
 * un tableau est passé par référence.
 * Le tableau reçu n'est jamais null.
 */
public class ArrayStats {

    /**
     * Renvoie la somme des valeurs (0 pour un tableau vide).
     * Attention : pourquoi le type de retour est-il long et pas int ? Les tests le vérifient.
     */
    public static long sum(int[] values) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la plus petite valeur. Le tableau contient au moins un élément.
     */
    public static int min(int[] values) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la plus grande valeur. Le tableau contient au moins un élément.
     */
    public static int max(int[] values) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la moyenne des valeurs, avec ses décimales (moyenne de {1, 2} = 1.5).
     * Renvoie 0.0 pour un tableau vide.
     */
    public static double average(int[] values) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la médiane : la valeur du milieu une fois le tableau trié,
     * ou la moyenne des deux valeurs du milieu si le nombre d'éléments est pair.
     * Exemples : median({3, 1, 2}) = 2.0, median({4, 1, 3, 2}) = 2.5. Renvoie 0.0 pour un tableau vide.
     * IMPORTANT : le tableau reçu ne doit PAS être modifié (l'appelant ne s'attend pas à ce qu'on le trie).
     */
    public static double median(int[] values) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Compte les valeurs strictement supérieures au seuil.
     * Exemple : countAbove({5, 10, 15}, 9.5) = 2.
     */
    public static int countAbove(int[] values, double threshold) {
        throw new UnsupportedOperationException("TODO");
    }
}
