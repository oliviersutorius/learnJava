package com.learnjava.m03.ex04;

/**
 * Exercice 4 (2/2) — Une bibliothèque.
 * Notions : tableau d'objets + compteur, références partagées, renvoyer null, ne pas exposer un tableau interne,
 * déléguer le travail aux objets.
 *
 * La bibliothèque contient au plus « capacity » livres, rangés dans l'ordre d'ajout.
 * Deux livres ne peuvent pas avoir le même ISBN.
 * Les ISBN se comparent exactement ; les noms d'auteurs sans tenir compte de la casse.
 *
 * TODO : déclare les attributs.
 */
public class Library {

    /**
     * Crée une bibliothèque vide. La capacité reçue n'est jamais négative.
     */
    public Library(int capacity) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le nombre de livres dans la bibliothèque.
     */
    public int size() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Indique si la bibliothèque est pleine.
     */
    public boolean isFull() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Ajoute un livre. Renvoie false (et ne change rien) si book est null, si la bibliothèque est pleine
     * ou si un livre de même ISBN est déjà présent.
     */
    public boolean addBook(Book book) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le livre qui a cet ISBN, ou null s'il n'y en a pas.
     */
    public Book findByIsbn(String isbn) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie les livres de cet auteur (casse ignorée), dans l'ordre d'ajout.
     * Le tableau renvoyé a exactement la taille nécessaire : il est vide (et pas null) si aucun livre ne correspond.
     */
    public Book[] findByAuthor(String author) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Emprunte le livre qui a cet ISBN. Renvoie false si le livre n'existe pas ou s'il est déjà emprunté.
     */
    public boolean borrow(String isbn) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Rend le livre qui a cet ISBN. Renvoie false si le livre n'existe pas ou s'il n'était pas emprunté.
     */
    public boolean giveBack(String isbn) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie le nombre de livres disponibles.
     */
    public int availableCount() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Renvoie les livres, dans l'ordre d'ajout, dans un tableau de taille size().
     * Modifier le tableau renvoyé ne doit pas modifier la bibliothèque.
     */
    public Book[] getBooks() {
        throw new UnsupportedOperationException("TODO");
    }
}
