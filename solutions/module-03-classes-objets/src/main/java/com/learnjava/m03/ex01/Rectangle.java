package com.learnjava.m03.ex01;

import java.util.Locale;

public class Rectangle {

    // Pas final : scale modifie le rectangle (objet « mutable »)
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        // Le constructeur garantit l'invariant « jamais de dimension négative »
        this.width = Math.max(0, width);
        this.height = Math.max(0, height);
    }

    public Rectangle(double side) {
        // this(...) réutilise l'autre constructeur, et donc sa règle sur les valeurs négatives
        this(side, side);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double area() {
        return width * height;
    }

    public double perimeter() {
        return 2 * (width + height);
    }

    public boolean isSquare() {
        // La comparaison est déjà un boolean : pas de if (règle 3.1)
        return width == height;
    }

    public void scale(double factor) {
        if (factor < 0) {
            return;
        }
        width *= factor;
        height *= factor;
    }

    public boolean canContain(Rectangle other) {
        // private concerne la classe : on peut lire other.width directement.
        // Si other est null, && s'arrête avant d'évaluer other.width (évaluation paresseuse).
        return other != null && other.width <= width && other.height <= height;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "Rectangle %.1f x %.1f", width, height);
    }
}
