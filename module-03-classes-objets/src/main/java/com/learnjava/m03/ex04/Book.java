package com.learnjava.m03.ex04;

/**
 * Exercice 4 (1/2) — Un livre de bibliothèque.
 * Notions : attributs final et non final dans une même classe, getter booléen (isXxx), méthodes qui changent l'état.
 *
 * L'ISBN, le titre et l'auteur ne changent jamais. Un livre est disponible à sa création ; il peut ensuite être
 * emprunté, puis rendu.
 */
public class Book {

    private final String isbn;
    private final String title;
    private final String author;

    private boolean available;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Indique si le livre est disponible (pas emprunté).
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * Emprunte le livre. Renvoie false (et ne change rien) s'il est déjà emprunté.
     */
    public boolean borrow() {
        if (!available) {
            return false;
        }

        available = false;

        return true;
    }

    /**
     * Rend le livre. Renvoie false (et ne change rien) s'il n'était pas emprunté.
     */
    public boolean giveBack() {
        if (available) {
            return false;
        }

        available = true;

        return true;
    }

    /**
     * Indique si l'auteur de ce livre est bien author.
     */
    public boolean isWrittenBy(String author) {
        return this.author.equalsIgnoreCase(author);
    }

    /**
     * Exemples : "Le Petit Prince (Saint-Exupéry) - disponible", "Dune (Herbert) - emprunté".
     */
    @Override
    public String toString() {
        String status = available ? "disponible" : "emprunté";

        return title + " (" + author + ") - " + status;
    }
}
