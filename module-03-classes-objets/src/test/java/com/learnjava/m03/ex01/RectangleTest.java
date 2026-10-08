package com.learnjava.m03.ex01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Ex01 - Rectangle")
class RectangleTest {

    private static final double PRECISION = 1e-9;

    @Test
    @DisplayName("Les attributs sont privés")
    void fieldsArePrivate() {
        assertThat(Rectangle.class.getDeclaredFields())
                .filteredOn(field -> !isConstant(field))
                .isNotEmpty()
                .allSatisfy(field -> assertThat(Modifier.isPrivate(field.getModifiers()))
                        .as("l'attribut %s doit être private", field.getName())
                        .isTrue());
    }

    @Test
    @DisplayName("Le constructeur enregistre la largeur et la hauteur")
    void constructor() {
        Rectangle rectangle = new Rectangle(3, 2.5);
        assertThat(rectangle.getWidth()).isEqualTo(3.0);
        assertThat(rectangle.getHeight()).isEqualTo(2.5);
    }

    @Test
    @DisplayName("Une dimension négative est remplacée par 0")
    void negativeDimensionsBecomeZero() {
        Rectangle rectangle = new Rectangle(3, -2);
        assertThat(rectangle.getWidth()).isEqualTo(3.0);
        assertThat(rectangle.getHeight()).isZero();
        assertThat(new Rectangle(-1, -1).area()).isZero();
        assertThat(new Rectangle(-4).getWidth()).isZero();
    }

    @Test
    @DisplayName("Le constructeur à un paramètre crée un carré")
    void squareConstructor() {
        Rectangle square = new Rectangle(4);
        assertThat(square.getWidth()).isEqualTo(4.0);
        assertThat(square.getHeight()).isEqualTo(4.0);
    }

    @ParameterizedTest(name = "{0} x {1} : aire {2}, périmètre {3}")
    @CsvSource({"3, 2, 6, 10", "1.5, 4, 6, 11", "0, 5, 0, 10", "10, 10, 100, 40"})
    void areaAndPerimeter(double width, double height, double expectedArea, double expectedPerimeter) {
        Rectangle rectangle = new Rectangle(width, height);
        assertThat(rectangle.area()).isCloseTo(expectedArea, within(PRECISION));
        assertThat(rectangle.perimeter()).isCloseTo(expectedPerimeter, within(PRECISION));
    }

    @Test
    void isSquare() {
        assertThat(new Rectangle(4).isSquare()).isTrue();
        assertThat(new Rectangle(2, 2).isSquare()).isTrue();
        assertThat(new Rectangle(2, 3).isSquare()).isFalse();
    }

    @Test
    @DisplayName("scale multiplie les deux dimensions et modifie le rectangle")
    void scale() {
        Rectangle rectangle = new Rectangle(3, 2);
        rectangle.scale(2);
        assertThat(rectangle.getWidth()).isEqualTo(6.0);
        assertThat(rectangle.getHeight()).isEqualTo(4.0);

        rectangle.scale(0.5);
        assertThat(rectangle.getWidth()).isEqualTo(3.0);
        assertThat(rectangle.getHeight()).isEqualTo(2.0);
    }

    @Test
    @DisplayName("scale ignore un facteur négatif mais accepte 0")
    void scaleWithNegativeOrZeroFactor() {
        Rectangle rectangle = new Rectangle(3, 2);
        rectangle.scale(-2);
        assertThat(rectangle.getWidth()).isEqualTo(3.0);
        assertThat(rectangle.getHeight()).isEqualTo(2.0);

        rectangle.scale(0);
        assertThat(rectangle.area()).isZero();
    }

    @Test
    @DisplayName("Deux variables peuvent désigner le même rectangle")
    void sharedReference() {
        Rectangle original = new Rectangle(3, 2);
        Rectangle alias = original;
        alias.scale(10);
        assertThat(original.getWidth()).isEqualTo(30.0);
    }

    @Test
    void canContain() {
        Rectangle box = new Rectangle(5, 3);
        assertThat(box.canContain(new Rectangle(5, 2))).isTrue();
        assertThat(box.canContain(new Rectangle(1, 1))).isTrue();
        assertThat(box.canContain(new Rectangle(5, 3))).as("même taille").isTrue();
        assertThat(box.canContain(box)).as("lui-même").isTrue();
        assertThat(box.canContain(new Rectangle(3, 5))).as("sans le tourner").isFalse();
        assertThat(box.canContain(new Rectangle(6, 1))).isFalse();
        assertThat(box.canContain(new Rectangle(1, 4))).isFalse();
    }

    @Test
    @DisplayName("canContain(null) renvoie false")
    void canContainNull() {
        assertThat(new Rectangle(5, 3).canContain(null)).isFalse();
    }

    @Test
    @DisplayName("toString affiche une décimale avec un point, quelle que soit la langue du système")
    void toStringFormat() {
        Locale previous = Locale.getDefault();
        try {
            Locale.setDefault(Locale.FRANCE);
            assertThat(new Rectangle(3, 2.5)).hasToString("Rectangle 3.0 x 2.5");
            assertThat(new Rectangle(10)).hasToString("Rectangle 10.0 x 10.0");
        } finally {
            Locale.setDefault(previous);
        }
    }

    /** Une constante (static final) peut être publique : seuls les autres attributs doivent être privés. */
    private static boolean isConstant(Field field) {
        return Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers());
    }
}
