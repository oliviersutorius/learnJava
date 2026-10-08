package com.learnjava.m03.miniprojet;

import java.util.Locale;

public class Contact {

    private static final String NO_EMAIL_LABEL = "-";

    private final String firstName;
    private final String lastName;
    private String phone;
    private String email;

    public Contact(String firstName, String lastName, String phone, String email) {
        this.firstName = firstName.strip();
        this.lastName = lastName.strip();
        // Les méthodes d'aide sont private static : le constructeur et les setters partagent le même nettoyage
        this.phone = cleanPhone(phone);
        this.email = cleanEmail(email);
    }

    public Contact(String firstName, String lastName, String phone) {
        this(firstName, lastName, phone, "");
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = cleanPhone(phone);
    }

    public void setEmail(String email) {
        this.email = cleanEmail(email);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getInitials() {
        // char + char serait une addition d'entiers (règle 1.1) : on part d'une String
        return "" + Character.toUpperCase(firstName.charAt(0)) + Character.toUpperCase(lastName.charAt(0));
    }

    public boolean hasEmail() {
        return !email.isEmpty();
    }

    public boolean matches(String query) {
        // Pas de cas particulier pour la recherche vide : "texte".contains("") vaut true
        String cleanedQuery = query.strip().toLowerCase(Locale.ROOT);
        return getFullName().toLowerCase(Locale.ROOT).contains(cleanedQuery) || phone.contains(cleanedQuery);
    }

    @Override
    public String toString() {
        String emailLabel = hasEmail() ? email : NO_EMAIL_LABEL;
        return getFullName() + " | " + phone + " | " + emailLabel;
    }

    private static String cleanPhone(String phone) {
        return phone.replace(" ", "");
    }

    private static String cleanEmail(String email) {
        return email.strip().toLowerCase(Locale.ROOT);
    }
}
