package com.learnjava.m02.miniprojet;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@DisplayName("Mini-projet - GradeBook")
class GradeBookTest {

    @Test
    void parseGrades() {
        assertThat(GradeBook.parseGrades("12 15.5 9")).containsExactly(12.0, 15.5, 9.0);
        assertThat(GradeBook.parseGrades("  14  ")).containsExactly(14.0);
        assertThat(GradeBook.parseGrades("8    20 0")).containsExactly(8.0, 20.0, 0.0);
    }

    @Test
    void parseGradesOfEmptyLine() {
        assertThat(GradeBook.parseGrades("")).isEmpty();
        assertThat(GradeBook.parseGrades("   ")).isEmpty();
    }

    @Test
    void average() {
        assertThat(GradeBook.average(new double[]{12, 16})).isCloseTo(14.0, within(1e-9));
        assertThat(GradeBook.average(new double[]{12, 15.5, 9})).isCloseTo(12.1666666, within(1e-6));
        assertThat(GradeBook.average(new double[]{})).isZero();
    }

    @ParameterizedTest(name = "moyenne {0} -> {1}")
    @CsvSource({
            "0, Ajourné", "9.99, Ajourné", "10, Passable", "11.99, Passable", "12, Assez bien", "13.5, Assez bien",
            "14, Bien", "15.99, Bien", "16, Très bien", "20, Très bien"
    })
    void mention(double average, String expected) {
        assertThat(GradeBook.mention(average)).isEqualTo(expected);
    }

    @Test
    void bestStudentIndex() {
        double[][] grades = {{10, 12}, {18, 16}, {14}};
        assertThat(GradeBook.bestStudentIndex(grades)).isEqualTo(1);
    }

    @Test
    @DisplayName("En cas d'égalité, le premier élève l'emporte")
    void bestStudentIndexTie() {
        assertThat(GradeBook.bestStudentIndex(new double[][]{{15}, {10, 20}, {15}})).isZero();
    }

    @Test
    @DisplayName("Un élève avec 0 de moyenne reste le meilleur s'il est seul")
    void bestStudentIndexWithZeroAverage() {
        assertThat(GradeBook.bestStudentIndex(new double[][]{{0, 0}})).isZero();
        assertThat(GradeBook.bestStudentIndex(new double[][]{{}})).isZero();
    }

    @Test
    void bestStudentIndexWithoutStudents() {
        assertThat(GradeBook.bestStudentIndex(new double[][]{})).isEqualTo(-1);
    }

    @Test
    void formatReport() {
        String[] names = {"Alice", "Bob"};
        double[][] grades = {{12, 16}, {9.5}};
        assertThat(GradeBook.formatReport(names, grades))
                .isEqualTo("Alice      14.00  Bien\nBob         9.50  Ajourné");
    }

    @Test
    void formatReportSingleStudent() {
        assertThat(GradeBook.formatReport(new String[]{"Chloé"}, new double[][]{{17, 18.5}}))
                .isEqualTo("Chloé      17.75  Très bien");
    }

    @Test
    @DisplayName("Le bulletin utilise un point décimal, même si la langue du système est le français")
    void formatReportIgnoresDefaultLocale() {
        Locale previous = Locale.getDefault();
        try {
            Locale.setDefault(Locale.FRANCE);
            assertThat(GradeBook.formatReport(new String[]{"Alice"}, new double[][]{{12, 16}}))
                    .isEqualTo("Alice      14.00  Bien");
        } finally {
            Locale.setDefault(previous);
        }
    }

    @Test
    void formatReportWithoutStudents() {
        assertThat(GradeBook.formatReport(new String[]{}, new double[][]{})).isEmpty();
    }
}
