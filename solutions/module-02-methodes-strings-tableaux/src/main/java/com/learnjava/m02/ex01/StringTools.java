package com.learnjava.m02.ex01;

public class StringTools {

    private static final String VOWELS = "aeiouy";

    public static String reverse(String text) {
        // StringBuilder est modifiable : on évite de créer une nouvelle String à chaque caractère
        return new StringBuilder(text).reverse().toString();
    }

    public static boolean isPalindrome(String text) {
        String cleaned = keepLettersAndDigits(text).toLowerCase();
        return cleaned.equals(reverse(cleaned));
    }

    // Méthode privée d'aide : un détail d'implémentation, invisible depuis les autres classes
    private static String keepLettersAndDigits(String text) {
        StringBuilder result = new StringBuilder(text.length());
        for (char c : text.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static int countVowels(String text) {
        int count = 0;
        for (char c : text.toLowerCase().toCharArray()) {
            // indexOf renvoie -1 si le caractère est absent
            if (VOWELS.indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public static String capitalize(String text) {
        StringBuilder result = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // Un caractère commence un mot s'il est en tête de texte ou précédé d'un espace :
            // pas besoin d'un booléen « startOfWord » à tenir à jour
            boolean startsWord = i == 0 || text.charAt(i - 1) == ' ';
            result.append(startsWord ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return result.toString();
    }
}
