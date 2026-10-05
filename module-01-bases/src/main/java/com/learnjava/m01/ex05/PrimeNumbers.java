package com.learnjava.m01.ex05;

/**
 * Exercice 5 — Nombres premiers.
 * Notions : boucles imbriquées, while, break / return anticipé, optimisation d'un algorithme.
 * Un nombre premier est un entier supérieur ou égal à 2 divisible uniquement par 1 et par lui-même.
 */
public class PrimeNumbers {

    /**
     * Indique si n est premier. Les nombres inférieurs à 2 ne sont pas premiers.
     * Bonus : il suffit de tester les diviseurs jusqu'à la racine carrée de n. Pourquoi ?
     * Attention : les tests utilisent Integer.MAX_VALUE (2 147 483 647), qui est premier.
     * Relis la section « débordement » du cours si ton programme se comporte bizarrement avec ce nombre.
     */
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        int limite = (int) Math.sqrt(n);

        for (int i = 3; i <= limite; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Compte les nombres premiers compris entre 2 et limit inclus.
     * Exemple : countPrimesUpTo(10) = 4 (2, 3, 5, 7).
     */
    public static int countPrimesUpTo(int limit) {
        int result = 0;
        for (int i = 1; i <= limit; i += 1 ) {
            if (isPrime(i)) {
                result += 1;
            }
        }

        return result;
    }

    /**
     * Renvoie le n-ième nombre premier (n commence à 1).
     * Exemples : nthPrime(1) = 2, nthPrime(4) = 7, nthPrime(100) = 541.
     * Astuce : on ne sait pas à l'avance jusqu'où chercher, une boucle while est donc plus adaptée qu'un for.
     */
    public static int nthPrime(int n) {
        int found = 0;
        int candidate = 1;
        while (found < n) {
            candidate++;
            if (isPrime(candidate)) {
                found++;
            }
        }
        return candidate;
    }
}
