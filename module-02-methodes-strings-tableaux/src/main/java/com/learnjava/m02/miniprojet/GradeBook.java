package com.learnjava.m02.miniprojet;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Mini-projet M02 — Carnet de notes.
 * <p>
 * Un programme console qui gère les notes (sur 20) d'une classe d'au plus MAX_STUDENTS élèves, avec un menu :
 * 1. Ajouter un élève et ses notes
 * 2. Afficher le bulletin de la classe
 * 3. Afficher le meilleur élève
 * 0. Quitter
 * <p>
 * Les données sont stockées dans deux tableaux « parallèles » : names[i] est le nom de l'élève i, grades[i] ses notes.
 * (Tu verras au module 5 qu'une liste d'objets est bien plus pratique : ce mini-projet te montrera pourquoi.)
 * <p>
 * Lancer le programme : ./mvnw -q -pl module-02-methodes-strings-tableaux compile exec:java
 */
public class GradeBook {

    public static final int MAX_STUDENTS = 30;

    public static final String FAILED = "Ajourné";
    public static final String PASS = "Passable";
    public static final String FAIRLY_GOOD = "Assez bien";
    public static final String GOOD = "Bien";
    public static final String VERY_GOOD = "Très bien";

    private static final double PASS_THRESHOLD = 10.0;
    private static final double FAIRLY_GOOD_THRESHOLD = 12.0;
    private static final double GOOD_THRESHOLD = 14.0;
    private static final double VERY_GOOD_THRESHOLD = 16.0;

    private static final String SEPARATORS = "\\s+";
    private static final String REPORT_LINE_FORMAT = "%-10s %5.2f  %s";

    /**
     * Transforme une ligne saisie au clavier en tableau de notes.
     * Les notes sont séparées par un ou plusieurs espaces et utilisent le point comme séparateur décimal.
     * Exemples : parseGrades("12 15.5 9") = {12.0, 15.5, 9.0}, parseGrades("  14  ") = {14.0}, parseGrades("") = {}.
     * On suppose que la ligne ne contient que des nombres valides.
     * Astuce : Double.parseDouble(String).
     */
    public static double[] parseGrades(String line) {
        if (line.isBlank()) {
            return new double[0];
        }

        String[] tokens = line.strip().split(SEPARATORS);
        double[] grades = new double[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            grades[i] = Double.parseDouble(tokens[i]);
        }

        return grades;
    }

    /**
     * Renvoie la moyenne des notes, ou 0.0 s'il n'y en a aucune.
     */
    public static double average(double[] grades) {
        if (grades.length == 0) {
            return 0.0;
        }

        double sum = 0.0;

        for (double grade : grades) {
            sum += grade;
        }

        return sum / grades.length;
    }

    /**
     * Renvoie la mention correspondant à une moyenne :
     * moins de 10 -> FAILED ; de 10 inclus à 12 exclu -> PASS ; de 12 à 14 -> FAIRLY_GOOD ;
     * de 14 à 16 -> GOOD ; 16 et plus -> VERY_GOOD.
     */
    public static String mention(double average) {
        if (average >= VERY_GOOD_THRESHOLD) {
            return VERY_GOOD;
        } else if (average >= GOOD_THRESHOLD) {
            return GOOD;
        } else if (average >= FAIRLY_GOOD_THRESHOLD) {
            return FAIRLY_GOOD;
        } else if (average >= PASS_THRESHOLD) {
            return PASS;
        }
        return FAILED;
    }

    /**
     * Renvoie l'indice de l'élève qui a la meilleure moyenne (le premier en cas d'égalité), ou -1 s'il n'y a aucun élève.
     * grades[i] contient les notes de l'élève i.
     */
    public static int bestStudentIndex(double[][] grades) {
        int bestIndex = -1;
        double bestAverage = 0;

        for (int i = 0; i < grades.length; i++) {
            double studentAverage = average(grades[i]);
            if (bestIndex == -1 || studentAverage > bestAverage) {
                bestIndex = i;
                bestAverage = studentAverage;
            }
        }

        return bestIndex;
    }

    /**
     * Construit le bulletin de la classe : une ligne par élève, dans l'ordre, séparées par "\n"
     * (pas de "\n" après la dernière ligne). Renvoie "" s'il n'y a aucun élève.
     * Chaque ligne contient : le nom aligné à gauche sur 10 caractères, un espace, la moyenne sur 5 caractères avec
     * 2 décimales et un POINT décimal, deux espaces, la mention.
     * Exemple pour Alice (12, 16) puis Bob (9.5) :
     * "Alice      14.00  Bien\nBob         9.50  Ajourné"
     * Astuces : String.format accepte un Locale en premier argument (le cours explique pourquoi c'est indispensable ici) ;
     * %-10s aligne une String à gauche sur 10 caractères ; StringBuilder pour assembler les lignes.
     */
    public static String formatReport(String[] names, double[][] grades) {
        StringBuilder report = new StringBuilder();

        for (int i = 0; i < names.length; i++) {
            if (i > 0) {
                report.append('\n');
            }
            double studentAverage = average(grades[i]);
            report.append(String.format(
                    Locale.ROOT,
                    REPORT_LINE_FORMAT,
                    names[i],
                    studentAverage,
                    mention(studentAverage)
            ));
        }

        return report.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] names = new String[MAX_STUDENTS];
        double[][] grades = new double[MAX_STUDENTS][];
        int studentCount = 0;

        boolean running = true;
        while (running) {
            printMenu();
            if (!scanner.hasNextLine()) {
                break;
            }

            String choice = scanner.nextLine().strip();
            switch (choice) {
                case "0":
                    running = false;
                    break;
                case "1":
                    if (studentCount == MAX_STUDENTS) {
                        System.out.println("La classe est complète (" + MAX_STUDENTS + " élèves).");
                        break;
                    }
                    System.out.print("Nom de l'élève : ");
                    names[studentCount] = scanner.nextLine().strip();
                    System.out.print("Notes (séparées par des espaces, ex. 12 15.5 9) : ");
                    grades[studentCount] = parseGrades(scanner.nextLine());
                    studentCount++;
                    break;
                case "2":
                    if (studentCount == 0) {
                        System.out.println("Il n'y a aucun élève dans la classe");
                    } else {
                        System.out.println(formatReport(
                                        Arrays.copyOf(names, studentCount),
                                        Arrays.copyOf(grades, studentCount)
                                )
                        );
                    }
                    break;
                case "3":
                    int best = bestStudentIndex(Arrays.copyOf(grades, studentCount));
                    if (best == -1) {
                        System.out.println("Aucun élève pour l'instant.");
                    } else {
                        double bestAverage = average(grades[best]);
                        System.out.printf(Locale.ROOT, "Meilleur élève : %s (%.2f, %s)%n",
                                names[best], bestAverage, mention(bestAverage));
                    }
                    break;
                default:
                    System.out.println("Choix inconnu : " + choice);
            }
        }

        System.out.println("Au revoir !");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Ajouter un élève et ses notes");
        System.out.println("2. Afficher le bulletin de la classe");
        System.out.println("3. Afficher le meilleur élève");
        System.out.println("0. Quitter");
        System.out.print("Ton choix : ");
    }
}
