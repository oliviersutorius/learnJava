package com.learnjava.m01.miniprojet;

import java.util.Random;
import java.util.Scanner;

/**
 * Mini-projet M01 — Jeu « Devine le nombre ».
 *
 * L'ordinateur choisit un nombre au hasard entre MIN et MAX. Le joueur propose des nombres
 * et le programme lui répond « C'est plus ! » ou « C'est moins ! » jusqu'à ce qu'il trouve.
 * À la fin, on affiche le nombre de tentatives.
 *
 * Lancer le jeu : ./mvnw -q -pl module-01-bases compile exec:java
 */
public class GuessTheNumber {

    public static final int MIN = 1;
    public static final int MAX = 100;

    public static final String TOO_LOW = "C'est plus !";
    public static final String TOO_HIGH = "C'est moins !";
    public static final String WIN = "Bravo !";

    /**
     * Renvoie l'indice à afficher au joueur : TOO_LOW si guess est trop petit,
     * TOO_HIGH si guess est trop grand, WIN s'il a trouvé.
     */
    public static String hint(int secret, int guess) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si guess est compris entre MIN et MAX inclus.
     */
    public static boolean isInRange(int guess) {
        throw new UnsupportedOperationException("TODO");
    }

    public static void main(String[] args) {
        // TODO — étapes suggérées :
        //  1. Créer un Scanner sur System.in et un Random.
        //  2. Tirer le nombre secret entre MIN et MAX inclus (regarde random.nextInt(origin, bound)).
        //  3. Boucler tant que le joueur n'a pas trouvé :
        //       - lire un entier (scanner.nextInt()),
        //       - s'il est hors de l'intervalle, l'indiquer sans compter la tentative,
        //       - sinon compter la tentative et afficher hint(...).
        //  4. Afficher le nombre de tentatives.
        //  Bonus : que se passe-t-il si le joueur tape « abc » ? Regarde scanner.hasNextInt().
        //  Bonus : limiter le nombre de tentatives à 7. Pourquoi 7 suffit-il toujours ?
        System.out.println("TODO : implémente le jeu !");
    }
}
