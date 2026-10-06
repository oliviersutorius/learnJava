package com.learnjava.m02.miniprojet;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Mini-projet M02 — Carnet de notes.
 *
 * Un programme console qui gère les notes (sur 20) d'une classe d'au plus MAX_STUDENTS élèves, avec un menu :
 *   1. Ajouter un élève et ses notes
 *   2. Afficher le bulletin de la classe
 *   3. Afficher le meilleur élève
 *   0. Quitter
 *
 * Les données sont stockées dans deux tableaux « parallèles » : names[i] est le nom de l'élève i, grades[i] ses notes.
 * (Tu verras au module 5 qu'une liste d'objets est bien plus pratique : ce mini-projet te montrera pourquoi.)
 *
 * Lancer le programme : ./mvnw -q -pl module-02-methodes-strings-tableaux compile exec:java
 */
public class GradeBook {

    public static final int MAX_STUDENTS = 30;

    public static final String FAILED = "Ajourné";
    public static final String PASS = "Passable";
    public static final String FAIRLY_GOOD = "Assez bien";
    public static final String GOOD = "Bien";
    public static final String VERY_GOOD = "Très bien";

    /**
     * Transforme une ligne saisie au clavier en tableau de notes.
     * Les notes sont séparées par un ou plusieurs espaces et utilisent le point comme séparateur décimal.
     * Exemples : parseGrades("12 15.5 9") = {12.0, 15.5, 9.0}, parseGrades("  14  ") = {14.0}, parseGrades("") = {}.
     * On suppose que la ligne ne contient que des nombres valides.
     * Astuce : Double.parseDouble(String).
     */
    public static double[] parseGrades(String line) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la moyenne des notes, ou 0.0 s'il n'y en a aucune.
     */
    public static double average(double[] grades) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie la mention correspondant à une moyenne :
     * moins de 10 -> FAILED ; de 10 inclus à 12 exclu -> PASS ; de 12 à 14 -> FAIRLY_GOOD ;
     * de 14 à 16 -> GOOD ; 16 et plus -> VERY_GOOD.
     */
    public static String mention(double average) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie l'indice de l'élève qui a la meilleure moyenne (le premier en cas d'égalité), ou -1 s'il n'y a aucun élève.
     * grades[i] contient les notes de l'élève i.
     */
    public static int bestStudentIndex(double[][] grades) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Construit le bulletin de la classe : une ligne par élève, dans l'ordre, séparées par "\n"
     * (pas de "\n" après la dernière ligne). Renvoie "" s'il n'y a aucun élève.
     * Chaque ligne contient : le nom aligné à gauche sur 10 caractères, un espace, la moyenne sur 5 caractères avec
     * 2 décimales et un POINT décimal, deux espaces, la mention.
     * Exemple pour Alice (12, 16) puis Bob (9.5) :
     *   "Alice      14.00  Bien\nBob         9.50  Ajourné"
     * Astuces : String.format accepte un Locale en premier argument (le cours explique pourquoi c'est indispensable ici) ;
     *           %-10s aligne une String à gauche sur 10 caractères ; StringBuilder pour assembler les lignes.
     */
    public static String formatReport(String[] names, double[][] grades) {
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        // TODO — étapes suggérées :
        //  1. Créer le Scanner, le tableau names (taille MAX_STUDENTS), le tableau grades (MAX_STUDENTS lignes)
        //     et un compteur d'élèves.
        //  2. Boucler tant que l'utilisateur ne choisit pas 0 : afficher le menu, lire le choix avec nextLine().
        //  3. Choix 1 : refuser si la classe est pleine ; sinon lire le nom puis la ligne de notes (parseGrades).
        //  4. Choix 2 : afficher formatReport(...) en ne passant que les élèves réellement saisis
        //     (regarde Arrays.copyOf) ; afficher un message si la classe est vide.
        //  5. Choix 3 : afficher le nom, la moyenne et la mention du meilleur élève.
        //  6. Choix inconnu : afficher un message d'erreur.
        //  Piège : mélanger nextInt() et nextLine() laisse un retour à la ligne non lu. Lis tout avec nextLine().
        //  Bonus : refuser une note hors de 0..20.
        //  Bonus : que se passe-t-il si on tape « douze » comme note ? (Tu sauras gérer ça proprement au module 5.)
        System.out.println("TODO : implémente le carnet de notes !");
    }
}
