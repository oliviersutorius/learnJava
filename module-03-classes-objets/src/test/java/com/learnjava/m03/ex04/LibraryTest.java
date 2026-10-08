package com.learnjava.m03.ex04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex04 - Library")
class LibraryTest {

    private Library library;
    private Book dune;
    private Book messiah;
    private Book prince;

    @BeforeEach
    void setUp() {
        library = new Library(5);
        dune = new Book("111", "Dune", "Frank Herbert");
        messiah = new Book("222", "Le Messie de Dune", "Frank Herbert");
        prince = new Book("333", "Le Petit Prince", "Antoine de Saint-Exupéry");
    }

    @Test
    @DisplayName("Les attributs sont privés")
    void fieldsArePrivate() {
        assertThat(Library.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field))
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers()))
                        .as("l'attribut %s doit être private", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Une nouvelle bibliothèque est vide")
    void newLibraryIsEmpty() {
        assertThat(library.size()).isZero();
        assertThat(library.isFull()).isFalse();
        assertThat(library.getBooks()).isEmpty();
        assertThat(library.availableCount()).isZero();
    }

    @Test
    void addBook() {
        assertThat(library.addBook(dune)).isTrue();
        assertThat(library.addBook(prince)).isTrue();
        assertThat(library.size()).isEqualTo(2);
        assertThat(library.getBooks()).containsExactly(dune, prince);
    }

    @Test
    @DisplayName("addBook refuse null et les ISBN en double")
    void addBookRejectsNullAndDuplicates() {
        library.addBook(dune);
        assertThat(library.addBook(null)).isFalse();
        assertThat(library.addBook(new Book("111", "Autre titre", "Autre auteur"))).as("ISBN déjà présent").isFalse();
        assertThat(library.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("addBook refuse un livre quand la bibliothèque est pleine")
    void addBookWhenFull() {
        Library small = new Library(2);
        assertThat(small.addBook(dune)).isTrue();
        assertThat(small.addBook(messiah)).isTrue();
        assertThat(small.isFull()).isTrue();
        assertThat(small.addBook(prince)).isFalse();
        assertThat(small.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Une bibliothèque de capacité 0 est pleine dès sa création")
    void zeroCapacity() {
        Library empty = new Library(0);
        assertThat(empty.isFull()).isTrue();
        assertThat(empty.addBook(dune)).isFalse();
    }

    @Test
    @DisplayName("findByIsbn renvoie le livre lui-même, ou null")
    void findByIsbn() {
        library.addBook(dune);
        library.addBook(prince);
        assertThat(library.findByIsbn("333")).isSameAs(prince);
        assertThat(library.findByIsbn("999")).isNull();
        assertThat(new Library(3).findByIsbn("111")).isNull();
    }

    @Test
    @DisplayName("findByAuthor ignore la casse et renvoie un tableau de la taille exacte")
    void findByAuthor() {
        library.addBook(dune);
        library.addBook(prince);
        library.addBook(messiah);
        assertThat(library.findByAuthor("frank HERBERT")).containsExactly(dune, messiah);
        assertThat(library.findByAuthor("Antoine de Saint-Exupéry")).containsExactly(prince);
        assertThat(library.findByAuthor("Herbert")).as("nom incomplet").isEmpty();
        assertThat(library.findByAuthor("Victor Hugo")).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Emprunter par la bibliothèque modifie le livre lui-même (même référence)")
    void borrowThroughLibrary() {
        library.addBook(dune);
        library.addBook(prince);
        assertThat(library.borrow("111")).isTrue();
        assertThat(dune.isAvailable()).isFalse();
        assertThat(library.availableCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("borrow refuse un livre inconnu ou déjà emprunté")
    void borrowRejected() {
        library.addBook(dune);
        library.borrow("111");
        assertThat(library.borrow("111")).isFalse();
        assertThat(library.borrow("999")).isFalse();
    }

    @Test
    void giveBack() {
        library.addBook(dune);
        library.borrow("111");
        assertThat(library.giveBack("111")).isTrue();
        assertThat(dune.isAvailable()).isTrue();
        assertThat(library.giveBack("111")).as("déjà rendu").isFalse();
        assertThat(library.giveBack("999")).as("livre inconnu").isFalse();
    }

    @Test
    void availableCount() {
        library.addBook(dune);
        library.addBook(messiah);
        library.addBook(prince);
        library.borrow("111");
        library.borrow("333");
        assertThat(library.availableCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("Modifier le tableau renvoyé par getBooks ne modifie pas la bibliothèque")
    void getBooksReturnsCopy() {
        library.addBook(dune);
        library.addBook(prince);

        Book[] books = library.getBooks();
        assertThat(books).hasSize(2);
        books[0] = null;
        books[1] = messiah;

        assertThat(library.getBooks()).containsExactly(dune, prince);
        assertThat(library.findByIsbn("111")).isSameAs(dune);
    }

    /** Une constante (static final) peut être publique : seuls les autres attributs doivent être privés. */
    private static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }
}
