package com.learnjava.m01.miniprojet;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static final int MIN = 1;
    public static final int MAX = 100;
    // Recherche dichotomique : 2^7 = 128 >= 100, donc 7 essais suffisent toujours en jouant bien
    public static final int MAX_ATTEMPTS = 7;

    public static final String TOO_LOW = "C'est plus !";
    public static final String TOO_HIGH = "C'est moins !";
    public static final String WIN = "Bravo !";

    public static String hint(int secret, int guess) {
        if (guess < secret) {
            return TOO_LOW;
        } else if (guess > secret) {
            return TOO_HIGH;
        }
        return WIN;
    }

    public static boolean isInRange(int guess) {
        return guess >= MIN && guess <= MAX;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int secret = random.nextInt(MIN, MAX + 1); // la borne haute est exclue

        System.out.println("J'ai choisi un nombre entre " + MIN + " et " + MAX + ". Tu as "
                + MAX_ATTEMPTS + " essais.");

        int attempts = 0;
        boolean found = false;
        while (!found && attempts < MAX_ATTEMPTS) {
            System.out.print("Ta proposition : ");
            if (!scanner.hasNextInt()) {
                if (!scanner.hasNext()) {
                    break; // fin de l'entrée (Ctrl+D)
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
            found = message.equals(WIN); // on compare des String avec equals, jamais avec ==
        }

        if (found) {
            System.out.println("Trouvé en " + attempts + " tentative(s).");
        } else {
            System.out.println("Perdu ! Le nombre était " + secret + ".");
        }
    }
}
