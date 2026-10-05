package com.learnjava.m01.ex03;

/**
 * Exercice 3 — FizzBuzz.
 * Notions : boucle for, modulo, ordre des conditions, concaténation de String.
 */
public class FizzBuzz {

    /**
     * Renvoie :
     * - "FizzBuzz" si n est divisible par 3 ET par 5,
     * - "Fizz" si n est divisible par 3,
     * - "Buzz" si n est divisible par 5,
     * - sinon le nombre lui-même sous forme de texte (ex. "7").
     */
    public static String fizzBuzz(int n) {
        String fizz = "";
        boolean threeOrFive = false;
        if (n % 3 == 0) {
            fizz += "Fizz";
            threeOrFive = true;
        }
        if (n % 5 == 0) {
            fizz += "Buzz";
            threeOrFive = true;
        }

        if (!threeOrFive) fizz += n;

        return fizz;
    }

    /**
     * Renvoie la suite FizzBuzz de 1 à n, séparée par des espaces, sans espace final.
     * Exemple : sequence(5) = "1 2 Fizz 4 Buzz".
     * Si n est inférieur ou égal à 0, renvoie une chaîne vide "".
     */
    public static String sequence(int n) {
        String retour = "";
        for (int i = 1; i <= n; i++) {
            if (i == 1 ) {
                retour += fizzBuzz(i);
            }
            else {
                retour += ' '+fizzBuzz(i);
            }

        }

        return retour;
    }
}
