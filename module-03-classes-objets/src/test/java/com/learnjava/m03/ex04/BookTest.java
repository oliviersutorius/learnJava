package com.learnjava.m03.ex04;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex04 - Book")
class BookTest {

    @Test
    @DisplayName("Les attributs sont privés")
    void fieldsArePrivate() {
        assertThat(Book.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field))
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers()))
                        .as("l'attribut %s doit être private", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Un nouveau livre est disponible")
    void newBook() {
        Book book = new Book("978-2070612758", "Le Petit Prince", "Saint-Exupéry");
        assertThat(book.getIsbn()).isEqualTo("978-2070612758");
        assertThat(book.getTitle()).isEqualTo("Le Petit Prince");
        assertThat(book.getAuthor()).isEqualTo("Saint-Exupéry");
        assertThat(book.isAvailable()).isTrue();
    }

    @Test
    @DisplayName("On emprunte puis on rend un livre")
    void borrowAndGiveBack() {
        Book book = new Book("1", "Dune", "Herbert");
        assertThat(book.borrow()).isTrue();
        assertThat(book.isAvailable()).isFalse();
        assertThat(book.giveBack()).isTrue();
        assertThat(book.isAvailable()).isTrue();
    }

    @Test
    @DisplayName("On ne peut pas emprunter un livre déjà emprunté")
    void cannotBorrowTwice() {
        Book book = new Book("1", "Dune", "Herbert");
        book.borrow();
        assertThat(book.borrow()).isFalse();
        assertThat(book.isAvailable()).isFalse();
    }

    @Test
    @DisplayName("On ne peut pas rendre un livre qui n'est pas emprunté")
    void cannotGiveBackAvailableBook() {
        Book book = new Book("1", "Dune", "Herbert");
        assertThat(book.giveBack()).isFalse();
        assertThat(book.isAvailable()).isTrue();
    }

    @Test
    void toStringFormat() {
        Book book = new Book("978-2070612758", "Le Petit Prince", "Saint-Exupéry");
        assertThat(book).hasToString("Le Petit Prince (Saint-Exupéry) - disponible");
        book.borrow();
        assertThat(book).hasToString("Le Petit Prince (Saint-Exupéry) - emprunté");
    }

    /** Une constante (static final) peut être publique : seuls les autres attributs doivent être privés. */
    private static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }
}
