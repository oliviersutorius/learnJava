package com.learnjava.m03.ex04;

public class Book {

    private final String isbn;
    private final String title;
    private final String author;
    // Le seul attribut qui change : il n'est pas final
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

    // Convention JavaBeans : isXxx pour un boolean
    public boolean isAvailable() {
        return available;
    }

    public boolean borrow() {
        if (!available) {
            return false;
        }
        available = false;
        return true;
    }

    public boolean giveBack() {
        if (available) {
            return false;
        }
        available = true;
        return true;
    }

    @Override
    public String toString() {
        String status = available ? "disponible" : "emprunté";
        return title + " (" + author + ") - " + status;
    }
}
