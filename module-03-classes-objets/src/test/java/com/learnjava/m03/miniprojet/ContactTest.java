package com.learnjava.m03.miniprojet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Mini-projet - Contact")
class ContactTest {

    @Test
    @DisplayName("Les attributs sont privés")
    void fieldsArePrivate() {
        assertThat(Contact.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field))
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers()))
                        .as("l'attribut %s doit être private", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Le prénom et le nom sont final, le téléphone et l'e-mail non")
    void finalFields() {
        assertThat(Contact.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field) && field.getType() == String.class)
                .filteredOn(field -> Modifier.isFinal(field.getModifiers()))
                .hasSize(2);
    }

    @Test
    void constructor() {
        Contact contact = new Contact("Ada", "Lovelace", "0612345678", "ada@example.com");
        assertThat(contact.getFirstName()).isEqualTo("Ada");
        assertThat(contact.getLastName()).isEqualTo("Lovelace");
        assertThat(contact.getPhone()).isEqualTo("0612345678");
        assertThat(contact.getEmail()).isEqualTo("ada@example.com");
    }

    @Test
    @DisplayName("Les valeurs sont nettoyées dans le constructeur")
    void constructorCleansValues() {
        Contact contact = new Contact("  Ada ", " Lovelace  ", "06 12 34 56 78", "  Ada@Example.COM ");
        assertThat(contact.getFirstName()).isEqualTo("Ada");
        assertThat(contact.getLastName()).isEqualTo("Lovelace");
        assertThat(contact.getPhone()).isEqualTo("0612345678");
        assertThat(contact.getEmail()).isEqualTo("ada@example.com");
    }

    @Test
    @DisplayName("Le constructeur à trois paramètres crée un contact sans e-mail")
    void constructorWithoutEmail() {
        Contact contact = new Contact("Alan", "Turing", "0698765432");
        assertThat(contact.getEmail()).isEmpty();
        assertThat(contact.hasEmail()).isFalse();
    }

    @Test
    @DisplayName("Les setters nettoient aussi les valeurs")
    void settersCleanValues() {
        Contact contact = new Contact("Ada", "Lovelace", "0612345678");
        contact.setPhone(" 07 00 00 00 01 ");
        contact.setEmail(" ADA@lovelace.ORG ");
        assertThat(contact.getPhone()).isEqualTo("0700000001");
        assertThat(contact.getEmail()).isEqualTo("ada@lovelace.org");
        assertThat(contact.hasEmail()).isTrue();

        contact.setEmail("   ");
        assertThat(contact.hasEmail()).as("un e-mail blanc signifie « pas d'e-mail »").isFalse();
    }

    @Test
    void fullName() {
        assertThat(new Contact(" Ada", "Lovelace ", "0").getFullName()).isEqualTo("Ada Lovelace");
    }

    @Test
    @DisplayName("Les initiales sont deux lettres majuscules, pas un nombre")
    void initials() {
        assertThat(new Contact("Ada", "Lovelace", "0").getInitials()).isEqualTo("AL");
        assertThat(new Contact("alan", "turing", "0").getInitials()).isEqualTo("AT");
        assertThat(new Contact("  émile ", "zola", "0").getInitials()).isEqualTo("ÉZ");
    }

    @ParameterizedTest(name = "\"{0}\" correspond à Ada Lovelace")
    @ValueSource(strings = {"love", "ADA L", "ada lovelace", "1234", "0612345678", "  lace  ", ""})
    void matches(String query) {
        assertThat(new Contact("Ada", "Lovelace", "06 12 34 56 78", "ada@example.com").matches(query)).isTrue();
    }

    @ParameterizedTest(name = "\"{0}\" ne correspond pas à Ada Lovelace")
    @ValueSource(strings = {"bob", "Lovelace Ada", "example", "0700"})
    void doesNotMatch(String query) {
        assertThat(new Contact("Ada", "Lovelace", "06 12 34 56 78", "ada@example.com").matches(query)).isFalse();
    }

    @Test
    void toStringFormat() {
        assertThat(new Contact("Ada", "Lovelace", "06 12 34 56 78", "ada@example.com"))
                .hasToString("Ada Lovelace | 0612345678 | ada@example.com");
        assertThat(new Contact("Alan", "Turing", "0698765432")).hasToString("Alan Turing | 0698765432 | -");
    }

    /** Une constante (static final) peut être publique : seuls les autres attributs doivent être privés. */
    private static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }
}
