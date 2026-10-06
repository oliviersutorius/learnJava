package com.learnjava.m02.ex03;

public class CaesarCipher {

    private static final int ALPHABET_SIZE = 26;
    private static final int CAESAR_SHIFT = 3;

    public static String encrypt(String text, int shift) {
        // En Java, -1 % 26 vaut -1 (le reste a le signe du dividende) : on ramène le décalage entre 0 et 25
        int normalizedShift = (shift % ALPHABET_SIZE + ALPHABET_SIZE) % ALPHABET_SIZE;

        StringBuilder result = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            result.append(shiftLetter(c, normalizedShift));
        }
        return result.toString();
    }

    public static String encrypt(String text) {
        return encrypt(text, CAESAR_SHIFT);
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, -shift);
    }

    public static String[] allShifts(String text) {
        String[] candidates = new String[ALPHABET_SIZE];
        for (int shift = 0; shift < ALPHABET_SIZE; shift++) {
            candidates[shift] = decrypt(text, shift);
        }
        return candidates;
    }

    private static char shiftLetter(char c, int shift) {
        if (c >= 'a' && c <= 'z') {
            return shiftFrom('a', c, shift);
        }
        if (c >= 'A' && c <= 'Z') {
            return shiftFrom('A', c, shift);
        }
        return c;
    }

    private static char shiftFrom(char firstLetter, char c, int shift) {
        // c - firstLetter : position de la lettre dans l'alphabet (0 à 25), un int.
        // Le résultat de l'addition est un int : le cast (char) est obligatoire pour revenir à un caractère.
        int position = (c - firstLetter + shift) % ALPHABET_SIZE;
        return (char) (firstLetter + position);
    }
}
