package com.learnjava.m03.ex04;

import java.util.Arrays;

public class Library {

    // Un tableau de capacité fixe + le nombre de cases réellement utilisées (de 0 à count - 1)
    private final Book[] books;
    private int count;

    public Library(int capacity) {
        books = new Book[capacity];
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isFull() {
        return count == books.length;
    }

    public boolean addBook(Book book) {
        if (book == null || isFull() || findByIsbn(book.getIsbn()) != null) {
            return false;
        }
        books[count] = book;
        count++;
        return true;
    }

    public Book findByIsbn(String isbn) {
        // On ne parcourt que les cases remplies : au-delà de count, les cases valent null
        for (int i = 0; i < count; i++) {
            if (books[i].getIsbn().equals(isbn)) {
                return books[i];
            }
        }
        return null;
    }

    public Book[] findByAuthor(String author) {
        // Même technique qu'au module 2 (WordCounter.words) : compter, créer à la bonne taille, remplir
        int matchCount = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                matchCount++;
            }
        }

        Book[] result = new Book[matchCount];
        int index = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                result[index] = books[i];
                index++;
            }
        }
        return result;
    }

    public boolean borrow(String isbn) {
        // La bibliothèque délègue au livre : c'est Book qui connaît la règle « pas deux emprunts »
        Book book = findByIsbn(isbn);
        return book != null && book.borrow();
    }

    public boolean giveBack(String isbn) {
        Book book = findByIsbn(isbn);
        return book != null && book.giveBack();
    }

    public int availableCount() {
        int available = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].isAvailable()) {
                available++;
            }
        }
        return available;
    }

    public Book[] getBooks() {
        // Une copie de la bonne taille : l'appelant ne peut pas modifier notre tableau interne.
        // Les livres eux-mêmes sont partagés (mêmes références) : c'est voulu.
        return Arrays.copyOf(books, count);
    }
}
