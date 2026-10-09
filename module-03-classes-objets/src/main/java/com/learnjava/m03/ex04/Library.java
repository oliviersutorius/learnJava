package com.learnjava.m03.ex04;

import java.util.Arrays;

/**
 * Exercice 4 (2/2) — Une bibliothèque.
 * Notions : tableau d'objets + compteur, références partagées, renvoyer null, ne pas exposer un tableau interne,
 * déléguer le travail aux objets.
 *
 * La bibliothèque contient au plus « capacity » livres, rangés dans l'ordre d'ajout.
 * Deux livres ne peuvent pas avoir le même ISBN.
 * Les ISBN se comparent exactement ; les noms d'auteurs sans tenir compte de la casse.
 */
public class Library {

    private int count;
    private final Book[] books;

    /**
     * Crée une bibliothèque vide. La capacité reçue n'est jamais négative.
     */
    public Library(int capacity) {
        books = new Book[capacity];
    }

    /**
     * Renvoie le nombre de livres dans la bibliothèque.
     */
    public int size() {
        return count;
    }

    /**
     * Indique si la bibliothèque est pleine.
     */
    public boolean isFull() {
        return count == books.length;
    }

    /**
     * Ajoute un livre. Renvoie false (et ne change rien) si book est null, si la bibliothèque est pleine
     * ou si un livre de même ISBN est déjà présent.
     */
    public boolean addBook(Book book) {
        if (book == null || isFull() || findByIsbn(book.getIsbn()) != null) {
            return false;
        }

        books[count] = book;
        count++;

        return true;
    }

    /**
     * Renvoie le livre qui a cet ISBN, ou null s'il n'y en a pas.
     */
    public Book findByIsbn(String isbn) {
        for (int i = 0; i < count; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                return books[i];
            }
        }

        return null;
    }

    /**
     * Renvoie les livres de cet auteur (casse ignorée), dans l'ordre d'ajout.
     * Le tableau renvoyé a exactement la taille nécessaire : il est vide (et pas null) si aucun livre ne correspond.
     */
    public Book[] findByAuthor(String author) {
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].isWrittenBy(author)) {
                matchCount++;
            }
        }

        Book[] result = new Book[matchCount];
        int index = 0;

        for (int i = 0; i < count; i++) {
            if (books[i].isWrittenBy(author)) {
                result[index] = books[i];
                index++;
            }
        }

        return result;
    }

    /**
     * Emprunte le livre qui a cet ISBN. Renvoie false si le livre n'existe pas ou s'il est déjà emprunté.
     */
    public boolean borrow(String isbn) {
        Book book = findByIsbn(isbn);

        return book != null && book.borrow();
    }

    /**
     * Rend le livre qui a cet ISBN. Renvoie false si le livre n'existe pas ou s'il n'était pas emprunté.
     */
    public boolean giveBack(String isbn) {
        Book book = findByIsbn(isbn);

        return book != null && book.giveBack();
    }

    /**
     * Renvoie le nombre de livres disponibles.
     */
    public int availableCount() {
        int available = 0;

        for (int i = 0; i < count; i++) {
            if (books[i].isAvailable()) {
                available++;
            }
        }

        return available;
    }

    /**
     * Renvoie les livres, dans l'ordre d'ajout, dans un tableau de taille size().
     * Modifier le tableau renvoyé ne doit pas modifier la bibliothèque.
     */
    public Book[] getBooks() {
        return Arrays.copyOf(books, count);
    }
}
