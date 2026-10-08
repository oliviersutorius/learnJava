package com.learnjava.m03.miniprojet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mini-projet - ContactBook")
class ContactBookTest {

    private ContactBook book;
    private Contact ada;
    private Contact alan;
    private Contact grace;

    @BeforeEach
    void setUp() {
        book = new ContactBook(3);
        ada = new Contact("Ada", "Lovelace", "0612345678", "ada@example.com");
        alan = new Contact("Alan", "Turing", "0698765432");
        grace = new Contact("Grace", "Hopper", "0711223344", "grace@navy.mil");
    }

    @Test
    @DisplayName("Les attributs sont privés")
    void fieldsArePrivate() {
        assertThat(ContactBook.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field))
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers()))
                        .as("l'attribut %s doit être private", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Un nouveau carnet est vide")
    void newBookIsEmpty() {
        assertThat(book.size()).isZero();
        assertThat(book.isFull()).isFalse();
        assertThat(book.getContacts()).isEmpty();
        assertThat(book.formatAll()).isEmpty();
    }

    @Test
    void add() {
        assertThat(book.add(ada)).isTrue();
        assertThat(book.add(alan)).isTrue();
        assertThat(book.size()).isEqualTo(2);
        assertThat(book.getContacts()).containsExactly(ada, alan);
    }

    @Test
    @DisplayName("add refuse null, les doublons de nom (casse ignorée) et un carnet plein")
    void addRejected() {
        book.add(ada);
        assertThat(book.add(null)).isFalse();
        assertThat(book.add(new Contact("ADA", "lovelace", "0700000000"))).as("doublon").isFalse();

        book.add(alan);
        book.add(grace);
        assertThat(book.isFull()).isTrue();
        assertThat(book.add(new Contact("Linus", "Torvalds", "0"))).as("carnet plein").isFalse();
        assertThat(book.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("findByName ignore la casse et les espaces autour, et renvoie null si absent")
    void findByName() {
        book.add(ada);
        book.add(alan);
        assertThat(book.findByName("Alan Turing")).isSameAs(alan);
        assertThat(book.findByName("  ada LOVELACE ")).isSameAs(ada);
        assertThat(book.findByName("Grace Hopper")).isNull();
        assertThat(book.findByName("Ada")).as("nom incomplet").isNull();
    }

    @Test
    @DisplayName("Modifier un contact trouvé modifie le contact du carnet")
    void findByNameReturnsSameObject() {
        book.add(ada);
        book.findByName("Ada Lovelace").setPhone("0700000000");
        assertThat(book.getContacts()[0].getPhone()).isEqualTo("0700000000");
    }

    @Test
    @DisplayName("remove supprime le contact et décale les suivants")
    void removeShiftsFollowingContacts() {
        book.add(ada);
        book.add(alan);
        book.add(grace);

        assertThat(book.remove("alan turing")).isTrue();
        assertThat(book.size()).isEqualTo(2);
        assertThat(book.getContacts()).containsExactly(ada, grace);
        assertThat(book.findByName("Alan Turing")).isNull();
        assertThat(book.isFull()).isFalse();
    }

    @Test
    @DisplayName("Après une suppression, on peut ajouter un contact à la fin")
    void addAfterRemove() {
        book.add(ada);
        book.add(alan);
        book.add(grace);
        book.remove("Ada Lovelace");

        Contact linus = new Contact("Linus", "Torvalds", "0");
        assertThat(book.add(linus)).isTrue();
        assertThat(book.getContacts()).containsExactly(alan, grace, linus);
    }

    @Test
    void removeFirstAndLast() {
        book.add(ada);
        book.add(alan);
        book.add(grace);
        assertThat(book.remove("Grace Hopper")).isTrue();
        assertThat(book.remove("Ada Lovelace")).isTrue();
        assertThat(book.getContacts()).containsExactly(alan);
    }

    @Test
    @DisplayName("remove renvoie false pour un nom inconnu")
    void removeUnknown() {
        book.add(ada);
        assertThat(book.remove("Bob Morane")).isFalse();
        assertThat(new ContactBook(2).remove("Ada Lovelace")).isFalse();
        assertThat(book.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("search renvoie les contacts correspondants dans un tableau de la taille exacte")
    void search() {
        book.add(ada);
        book.add(alan);
        book.add(grace);
        assertThat(book.search("a")).containsExactly(ada, alan, grace);
        assertThat(book.search("TUR")).containsExactly(alan);
        assertThat(book.search("06")).containsExactly(ada, alan);
        assertThat(book.search("xyz")).isNotNull().isEmpty();
    }

    @Test
    @DisplayName("Modifier le tableau renvoyé par getContacts ne modifie pas le carnet")
    void getContactsReturnsCopy() {
        book.add(ada);
        Contact[] contacts = book.getContacts();
        contacts[0] = alan;
        assertThat(book.getContacts()).containsExactly(ada);
    }

    @Test
    void formatAll() {
        book.add(ada);
        book.add(alan);
        assertThat(book.formatAll())
                .isEqualTo("Ada Lovelace | 0612345678 | ada@example.com\nAlan Turing | 0698765432 | -");
    }

    /** Une constante (static final) peut être publique : seuls les autres attributs doivent être privés. */
    private static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }
}
