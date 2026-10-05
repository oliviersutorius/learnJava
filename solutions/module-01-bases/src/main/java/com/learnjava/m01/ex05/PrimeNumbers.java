package com.learnjava.m01.ex05;

public class PrimeNumbers {

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        // Si n = a × b avec a <= b, alors a <= √n : inutile de chercher un diviseur au-delà.
        // Piège : « i * i <= n » semble naturel, mais pour n proche de Integer.MAX_VALUE, i * i dépasse
        // la capacité d'un int (overflow) et devient négatif : la boucle ne s'arrête plus au bon moment.
        // « i <= n / i » est équivalent mathématiquement et ne déborde jamais.
        for (int i = 2; i <= n / i; i++) {
            if (n % i == 0) {
                return false; // retour anticipé : dès qu'on trouve un diviseur, c'est terminé
            }
        }
        return true;
    }

    public static int countPrimesUpTo(int limit) {
        int count = 0;
        for (int i = 2; i <= limit; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

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
