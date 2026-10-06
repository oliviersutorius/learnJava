package com.learnjava.m02.ex05;

/**
 * Exercice 5 — Opérations sur les matrices.
 * Notions : tableaux à deux dimensions (int[][]), boucles imbriquées, matrix.length (lignes) et matrix[0].length
 * (colonnes), créer un tableau 2D de la bonne taille.
 * Toutes les matrices reçues sont rectangulaires (toutes les lignes ont la même longueur), non vides et non null.
 */
public class MatrixOps {

    /**
     * Renvoie la matrice identité de taille size × size : des 1 sur la diagonale, des 0 ailleurs.
     * Exemple : identity(2) = {{1, 0}, {0, 1}}.
     */
    public static int[][] identity(int size) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la transposée : la ligne i devient la colonne i.
     * Exemple : transpose({{1, 2, 3}, {4, 5, 6}}) = {{1, 4}, {2, 5}, {3, 6}}.
     * La matrice reçue ne doit pas être modifiée.
     */
    public static int[][] transpose(int[][] matrix) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Additionne deux matrices de mêmes dimensions, case par case.
     */
    public static int[][] add(int[][] a, int[][] b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Produit matriciel a × b. Le nombre de colonnes de a est égal au nombre de lignes de b.
     * La case [i][j] du résultat est la somme des a[i][k] × b[k][j] pour tous les k.
     * Exemple : {{1, 2}, {3, 4}} × {{5, 6}, {7, 8}} = {{19, 22}, {43, 50}}.
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si la matrice est symétrique : carrée, et matrix[i][j] == matrix[j][i] pour tous i et j.
     * Une matrice non carrée n'est pas symétrique.
     */
    public static boolean isSymmetric(int[][] matrix) {
        throw new UnsupportedOperationException("TODO");
    }
}
