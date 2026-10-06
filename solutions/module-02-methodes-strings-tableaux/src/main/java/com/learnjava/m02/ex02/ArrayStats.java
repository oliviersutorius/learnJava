package com.learnjava.m02.ex02;

import java.util.Arrays;

public class ArrayStats {

    public static long sum(int[] values) {
        // Accumuler dans un long : la somme de plusieurs int peut dépasser Integer.MAX_VALUE
        long total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    public static int min(int[] values) {
        int min = values[0];
        for (int value : values) {
            min = Math.min(min, value);
        }
        return min;
    }

    public static int max(int[] values) {
        int max = values[0];
        for (int value : values) {
            max = Math.max(max, value);
        }
        return max;
    }

    public static double average(int[] values) {
        if (values.length == 0) {
            return 0.0;
        }
        // Le cast porte sur une variable : il est nécessaire, sinon long / int = division entière
        return (double) sum(values) / values.length;
    }

    public static double median(int[] values) {
        if (values.length == 0) {
            return 0.0;
        }
        // Le tableau est passé par référence : trier « values » modifierait celui de l'appelant.
        int[] sorted = Arrays.copyOf(values, values.length);
        Arrays.sort(sorted);

        int middle = sorted.length / 2;
        if (sorted.length % 2 == 1) {
            return sorted[middle];
        }
        // (long) avant l'addition : deux grands int additionnés débordent
        return ((long) sorted[middle - 1] + sorted[middle]) / 2.0;
    }

    public static int countAbove(int[] values, double threshold) {
        int count = 0;
        for (int value : values) {
            if (value > threshold) {
                count++;
            }
        }
        return count;
    }
}
