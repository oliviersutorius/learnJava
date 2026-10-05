package com.learnjava.m01.ex03;

public class FizzBuzz {

    public static String fizzBuzz(int n) {
        // Le cas le plus spécifique (divisible par 15) doit être testé en premier
        if (n % 15 == 0) {
            return "FizzBuzz";
        } else if (n % 3 == 0) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        }
        return String.valueOf(n);
    }

    public static String sequence(int n) {
        String result = "";
        for (int i = 1; i <= n; i++) {
            if (i > 1) {
                result += " ";
            }
            result += fizzBuzz(i);
        }
        // Concaténer dans une boucle crée un nouveau String à chaque tour : StringBuilder (module 2) est plus efficace
        return result;
    }
}
