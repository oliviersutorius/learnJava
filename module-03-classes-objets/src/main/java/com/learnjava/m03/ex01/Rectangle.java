package com.learnjava.m03.ex01;

import java.util.Locale;

/**
 * Exercice 1 — Un rectangle.
 * Notions : attributs privés, constructeurs et this(...), getters, méthodes d'instance, objet reçu en paramètre,
 * toString.
 * <p>
 * Un rectangle a une largeur et une hauteur (des double). Elles ne sont jamais négatives.
 */
public class Rectangle {

    private double width;
    private double height;

    /**
     * Crée un rectangle. Une dimension négative est remplacée par 0.
     * Exemple : new Rectangle(3, -2) a une largeur de 3.0 et une hauteur de 0.0.
     */
    public Rectangle(double width, double height) {
        this.width = Math.max(0, width);
        this.height = Math.max(0, height);
    }

    /**
     * Crée un carré de côté side (même règle pour un côté négatif).
     * Astuce : une seule ligne, qui appelle l'autre constructeur.
     */
    public Rectangle(double side) {
        this(side, side);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Renvoie l'aire. Exemple : 3 x 2 -> 6.0.
     */
    public double area() {
        return width * height;
    }

    /**
     * Renvoie le périmètre. Exemple : 3 x 2 -> 10.0.
     */
    public double perimeter() {
        return (width + height) * 2;
    }

    /**
     * Indique si le rectangle est un carré (largeur et hauteur exactement égales).
     */
    public boolean isSquare() {
        return width == height;
    }

    /**
     * Multiplie la largeur et la hauteur par factor (ce rectangle est modifié).
     * Un facteur négatif est ignoré : le rectangle ne change pas. Un facteur 0 est accepté.
     */
    public void scale(double factor) {
        if (factor < 0) {
            return;
        }
        width *= factor;
        height *= factor;
    }

    /**
     * Indique si other peut être posé à l'intérieur de ce rectangle, sans le tourner :
     * sa largeur et sa hauteur ne dépassent pas celles de ce rectangle (l'égalité est acceptée).
     * Renvoie false si other est null.
     * Exemples : un 5 x 3 peut contenir un 5 x 2, mais pas un 3 x 5.
     */
    public boolean canContain(Rectangle other) {
        return other != null && width >= other.width && height >= other.height;
    }

    /**
     * Exemple : "Rectangle 3.0 x 2.5" (une décimale, toujours un point décimal).
     */
    @Override
    public String toString() {
        return String.format(Locale.ROOT, "Rectangle %.1f x %.1f", width, height);
    }
}
