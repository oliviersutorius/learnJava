package com.learnjava.m03.ex04;

/**
 * Exercice 4 (1/2) — Un livre de bibliothèque.
 * Notions : attributs final et non final dans une même classe, getter booléen (isXxx), méthodes qui changent l'état.
 *
 * L'ISBN, le titre et l'auteur ne changent jamais. Un livre est disponible à sa création ; il peut ensuite être
 * emprunté, puis rendu.
 *
 * TODO : déclare les attributs.
 */
public class Book {

    public Book(String isbn, String title, String author) {
        throw new UnsupportedOperationException("TODO");
    }

    public String getIsbn() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getTitle() {
        throw new UnsupportedOperationException("TODO");
    }

    public String getAuthor() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si le livre est disponible (pas emprunté).
     */
    public boolean isAvailable() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Emprunte le livre. Renvoie false (et ne change rien) s'il est déjà emprunté.
     */
    public boolean borrow() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Rend le livre. Renvoie false (et ne change rien) s'il n'était pas emprunté.
     */
    public boolean giveBack() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Exemples : "Le Petit Prince (Saint-Exupéry) - disponible", "Dune (Herbert) - emprunté".
     */
    @Override
    public String toString() {
        throw new UnsupportedOperationException("TODO");
    }
}
