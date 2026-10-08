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
        int[][] result = new int[size][size];

        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }

        return result;
    }

    /**
     * Renvoie la transposée : la ligne i devient la colonne i.
     * Exemple : transpose({{1, 2, 3}, {4, 5, 6}}) = {{1, 4}, {2, 5}, {3, 6}}.
     * La matrice reçue ne doit pas être modifiée.
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    /**
     * Additionne deux matrices de mêmes dimensions, case par case.
     */
    public static int[][] add(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = a[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }

        return result;
    }

    /**
     * Produit matriciel a × b. Le nombre de colonnes de a est égal au nombre de lignes de b.
     * La case [i][j] du résultat est la somme des a[i][k] × b[k][j] pour tous les k.
     * Exemple : {{1, 2}, {3, 4}} × {{5, 6}, {7, 8}} = {{19, 22}, {43, 50}}.
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        int rows = a.length;
        int cols = b[0].length;
        int common = b.length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int sum = 0;
                for (int k = 0; k < common; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }

        return result;
    }

    /**
     * Indique si la matrice est symétrique : carrée, et matrix[i][j] == matrix[j][i] pour tous i et j.
     * Une matrice non carrée n'est pas symétrique.
     */
    public static boolean isSymmetric(int[][] matrix) {
        int size = matrix.length;
        if (matrix[0].length != size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
