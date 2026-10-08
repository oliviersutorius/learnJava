package com.learnjava.m03.miniprojet;

import java.util.Arrays;

public class ContactBook {

    private final Contact[] contacts;
    private int count;

    public ContactBook(int capacity) {
        contacts = new Contact[capacity];
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isFull() {
        return count == contacts.length;
    }

    public boolean add(Contact contact) {
        if (contact == null || isFull() || findByName(contact.getFullName()) != null) {
            return false;
        }
        contacts[count] = contact;
        count++;
        return true;
    }

    public Contact findByName(String fullName) {
        int index = indexOf(fullName);
        return index == -1 ? null : contacts[index];
    }

    public boolean remove(String fullName) {
        int index = indexOf(fullName);
        if (index == -1) {
            return false;
        }
        // Décale d'une case vers la gauche tous les contacts qui suivent
        for (int i = index; i < count - 1; i++) {
            contacts[i] = contacts[i + 1];
        }
        count--;
        // La dernière case n'est plus utilisée : on la vide pour ne pas garder une référence inutile
        contacts[count] = null;
        return true;
    }

    public Contact[] search(String query) {
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (contacts[i].matches(query)) {
                matchCount++;
            }
        }

        Contact[] result = new Contact[matchCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (contacts[i].matches(query)) {
                result[index] = contacts[i];
                index++;
            }
        }
        return result;
    }

    public Contact[] getContacts() {
        return Arrays.copyOf(contacts, count);
    }

    public String formatAll() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                report.append('\n');
            }
            // append(Object) appelle toString() automatiquement
            report.append(contacts[i]);
        }
        return report.toString();
    }

    // Méthode d'aide partagée par findByName et remove : renvoie l'indice du contact, ou -1
    private int indexOf(String fullName) {
        String cleanedName = fullName.strip();
        for (int i = 0; i < count; i++) {
            if (contacts[i].getFullName().equalsIgnoreCase(cleanedName)) {
                return i;
            }
        }
        return -1;
    }
}
