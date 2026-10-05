package com.learnjava.m01.miniprojet;

import java.util.Random;
import java.util.Scanner;

/**
 * Mini-projet M01 — Jeu « Devine le nombre ».
 * <p>
 * L'ordinateur choisit un nombre au hasard entre MIN et MAX. Le joueur propose des nombres
 * et le programme lui répond « C'est plus ! » ou « C'est moins ! » jusqu'à ce qu'il trouve.
 * À la fin, on affiche le nombre de tentatives.
 * <p>
 * Lancer le jeu : ./mvnw -q -pl module-01-bases compile exec:java
 */
public class GuessTheNumber {

    public static final int MIN = 1;
    public static final int MAX = 100;
    public static final int MAX_ATTEMPTS = 7;

    public static final String TOO_LOW = "C'est plus !";
    public static final String TOO_HIGH = "C'est moins !";
    public static final String WIN = "Bravo !";

    /**
     * Renvoie l'indice à afficher au joueur : TOO_LOW si guess est trop petit,
     * TOO_HIGH si guess est trop grand, WIN s'il a trouvé.
     */
    public static String hint(int secret, int guess) {
        if (guess == secret) {
            return WIN;
        }
        return guess < secret ? TOO_LOW : TOO_HIGH;
    }

    /**
     * Indique si guess est compris entre MIN et MAX inclus.
     */
    public static boolean isInRange(int guess) {
        return guess >= MIN && guess <= MAX;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secret = random.nextInt(MIN, MAX + 1);

        System.out.println("J'ai choisi un nombre entre " + MIN + " et " + MAX + ". Tu as "
                + MAX_ATTEMPTS + " essais.");

        int attempts = 0;
        boolean found = false;
        while (!found && attempts < MAX_ATTEMPTS) {
            System.out.print("Ta proposition : ");
            if (!scanner.hasNextInt()) {
                if (!scanner.hasNext()) {
                    break;
                }
                System.out.println("« " + scanner.next() + " » n'est pas un nombre entier.");
                continue;
            }
            int guess = scanner.nextInt();
            if (!isInRange(guess)) {
                System.out.println("Le nombre doit être entre " + MIN + " et " + MAX + ".");
                continue;
            }
            attempts++;
            String message = hint(secret, guess);
            System.out.println(message);
            found = guess == secret;
        }

        if (found) {
            System.out.println("Trouvé en " + attempts + " tentative(s).");
        } else {
            System.out.println("Perdu ! Le nombre était " + secret + ".");
        }
    }
}
