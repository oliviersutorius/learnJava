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
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Compte les nombres premiers compris entre 2 et limit inclus.
     * Exemple : countPrimesUpTo(10) = 4 (2, 3, 5, 7).
     */
    public static int countPrimesUpTo(int limit) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le n-ième nombre premier (n commence à 1).
     * Exemples : nthPrime(1) = 2, nthPrime(4) = 7, nthPrime(100) = 541.
     * Astuce : on ne sait pas à l'avance jusqu'où chercher, une boucle while est donc plus adaptée qu'un for.
     */
    public static int nthPrime(int n) {
        throw new UnsupportedOperationException("TODO");
    }
}
