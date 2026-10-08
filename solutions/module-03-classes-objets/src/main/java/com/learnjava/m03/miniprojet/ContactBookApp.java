package com.learnjava.m03.miniprojet;

import java.util.Scanner;

public class ContactBookApp {

    public static final int MAX_CONTACTS = 50;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactBook book = new ContactBook(MAX_CONTACTS);

        boolean running = true;
        while (running) {
            printMenu();
            if (!scanner.hasNextLine()) {
                break;
            }
            String choice = scanner.nextLine().strip();
            switch (choice) {
                case "1":
                    addContact(scanner, book);
                    break;
                case "2":
                    listContacts(book);
                    break;
                case "3":
                    searchContacts(scanner, book);
                    break;
                case "4":
                    editContact(scanner, book);
                    break;
                case "5":
                    removeContact(scanner, book);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Choix inconnu : " + choice);
            }
        }
        System.out.println("Au revoir !");
    }

    // Une méthode par choix du menu : main reste lisible, et chaque variable locale vit dans sa propre méthode

    private static void addContact(Scanner scanner, ContactBook book) {
        if (book.isFull()) {
            System.out.println("Le carnet est plein (" + MAX_CONTACTS + " contacts).");
            return;
        }
        String firstName = ask(scanner, "Prénom : ");
        String lastName = ask(scanner, "Nom : ");
        String phone = ask(scanner, "Téléphone : ");
        String email = ask(scanner, "E-mail (vide si aucun) : ");
        if (firstName.isBlank() || lastName.isBlank()) {
            System.out.println("Le prénom et le nom sont obligatoires.");
            return;
        }
        Contact contact = new Contact(firstName, lastName, phone, email);
        if (book.add(contact)) {
            System.out.println("Contact ajouté : " + contact);
        } else {
            System.out.println("Un contact nommé " + contact.getFullName() + " existe déjà.");
        }
    }

    private static void listContacts(ContactBook book) {
        if (book.size() == 0) {
            System.out.println("Le carnet est vide.");
        } else {
            System.out.println(book.formatAll());
        }
    }

    private static void searchContacts(Scanner scanner, ContactBook book) {
        Contact[] results = book.search(ask(scanner, "Recherche : "));
        if (results.length == 0) {
            System.out.println("Aucun résultat.");
        }
        for (Contact contact : results) {
            System.out.println(contact);
        }
    }

    private static void editContact(Scanner scanner, ContactBook book) {
        Contact contact = book.findByName(ask(scanner, "Nom complet du contact : "));
        // findByName renvoie null si le contact n'existe pas : on vérifie avant de l'utiliser
        if (contact == null) {
            System.out.println("Contact introuvable.");
            return;
        }
        String phone = ask(scanner, "Nouveau téléphone (vide pour ne pas changer) : ");
        if (!phone.isBlank()) {
            contact.setPhone(phone);
        }
        String email = ask(scanner, "Nouvel e-mail (vide pour ne pas changer) : ");
        if (!email.isBlank()) {
            contact.setEmail(email);
        }
        // Le contact modifié est celui du carnet (même référence) : rien d'autre à faire
        System.out.println("Contact modifié : " + contact);
    }

    private static void removeContact(Scanner scanner, ContactBook book) {
        String fullName = ask(scanner, "Nom complet du contact à supprimer : ");
        if (book.remove(fullName)) {
            System.out.println("Contact supprimé.");
        } else {
            System.out.println("Contact introuvable.");
        }
    }

    private static String ask(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Ajouter un contact");
        System.out.println("2. Afficher tous les contacts");
        System.out.println("3. Rechercher");
        System.out.println("4. Modifier le téléphone ou l'e-mail d'un contact");
        System.out.println("5. Supprimer un contact");
        System.out.println("0. Quitter");
        System.out.print("Ton choix : ");
    }
}
