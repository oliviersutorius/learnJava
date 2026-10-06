package com.learnjava.m02.miniprojet;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class GradeBook {

    public static final int MAX_STUDENTS = 30;

    public static final String FAILED = "Ajourné";
    public static final String PASS = "Passable";
    public static final String FAIRLY_GOOD = "Assez bien";
    public static final String GOOD = "Bien";
    public static final String VERY_GOOD = "Très bien";

    private static final double PASS_THRESHOLD = 10;
    private static final double FAIRLY_GOOD_THRESHOLD = 12;
    private static final double GOOD_THRESHOLD = 14;
    private static final double VERY_GOOD_THRESHOLD = 16;
    private static final String REPORT_LINE_FORMAT = "%-10s %5.2f  %s";

    public static double[] parseGrades(String line) {
        if (line.isBlank()) {
            return new double[0];
        }
        String[] tokens = line.strip().split("\\s+");
        double[] grades = new double[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            // parseDouble attend toujours un point décimal, quelle que soit la langue du système
            grades[i] = Double.parseDouble(tokens[i]);
        }
        return grades;
    }

    public static double average(double[] grades) {
        if (grades.length == 0) {
            return 0.0;
        }
        double total = 0;
        for (double grade : grades) {
            total += grade;
        }
        return total / grades.length;
    }

    public static String mention(double average) {
        // Du seuil le plus haut au plus bas : chaque test n'a besoin que d'une seule borne
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

    public static String formatReport(String[] names, double[][] grades) {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < names.length; i++) {
            if (i > 0) {
                report.append('\n');
            }
            double studentAverage = average(grades[i]);
            // Sans Locale, %.2f utilise la langue du système : « 14,00 » sur un poste français, « 14.00 » ailleurs
            report.append(String.format(Locale.ROOT, REPORT_LINE_FORMAT, names[i], studentAverage, mention(studentAverage)));
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
                break; // fin de l'entrée (Ctrl+D)
            }
            String choice = scanner.nextLine().strip();
            switch (choice) {
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
                        System.out.println("Aucun élève pour l'instant.");
                    } else {
                        // On ne transmet que les cases remplies : les suivantes contiennent null
                        System.out.println(formatReport(Arrays.copyOf(names, studentCount),
                                Arrays.copyOf(grades, studentCount)));
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
                case "0":
                    running = false;
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
