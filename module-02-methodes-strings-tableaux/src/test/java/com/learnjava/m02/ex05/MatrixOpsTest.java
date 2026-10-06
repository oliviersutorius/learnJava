package com.learnjava.m02.ex05;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Ex05 - MatrixOps")
class MatrixOpsTest {

    @Test
    void identity() {
        assertThat(MatrixOps.identity(1)).isDeepEqualTo(new int[][]{{1}});
        assertThat(MatrixOps.identity(3)).isDeepEqualTo(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}});
    }

    @Test
    void transposeRectangular() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}};
        assertThat(MatrixOps.transpose(matrix)).isDeepEqualTo(new int[][]{{1, 4}, {2, 5}, {3, 6}});
    }

    @Test
    void transposeSquareAndSingleRow() {
        assertThat(MatrixOps.transpose(new int[][]{{1, 2}, {3, 4}})).isDeepEqualTo(new int[][]{{1, 3}, {2, 4}});
        assertThat(MatrixOps.transpose(new int[][]{{7, 8, 9}})).isDeepEqualTo(new int[][]{{7}, {8}, {9}});
    }

    @Test
    @DisplayName("transpose ne modifie pas la matrice reçue")
    void transposeDoesNotModifyInput() {
        int[][] matrix = {{1, 2}, {3, 4}};
        MatrixOps.transpose(matrix);
        assertThat(matrix).isDeepEqualTo(new int[][]{{1, 2}, {3, 4}});
    }

    @Test
    void add() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};
        int[][] b = {{10, 20, 30}, {-4, -5, -6}};
        assertThat(MatrixOps.add(a, b)).isDeepEqualTo(new int[][]{{11, 22, 33}, {0, 0, 0}});
        assertThat(a).isDeepEqualTo(new int[][]{{1, 2, 3}, {4, 5, 6}});
    }

    @Test
    void multiplySquare() {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        assertThat(MatrixOps.multiply(a, b)).isDeepEqualTo(new int[][]{{19, 22}, {43, 50}});
    }

    @Test
    void multiplyRectangular() {
        int[][] a = {{1, 2, 3}, {4, 5, 6}};      // 2 x 3
        int[][] b = {{7, 8}, {9, 10}, {11, 12}}; // 3 x 2
        assertThat(MatrixOps.multiply(a, b)).isDeepEqualTo(new int[][]{{58, 64}, {139, 154}});
        assertThat(MatrixOps.multiply(b, a)).isDeepEqualTo(new int[][]{{39, 54, 69}, {49, 68, 87}, {59, 82, 105}});
    }

    @Test
    @DisplayName("Multiplier par l'identité ne change pas la matrice")
    void multiplyByIdentity() {
        int[][] matrix = {{2, -1, 4}, {0, 3, 5}, {7, 8, 9}};
        assertThat(MatrixOps.multiply(matrix, MatrixOps.identity(3))).isDeepEqualTo(matrix);
        assertThat(MatrixOps.multiply(MatrixOps.identity(3), matrix)).isDeepEqualTo(matrix);
    }

    @Test
    void isSymmetric() {
        assertThat(MatrixOps.isSymmetric(new int[][]{{1, 2, 3}, {2, 5, 6}, {3, 6, 9}})).isTrue();
        assertThat(MatrixOps.isSymmetric(MatrixOps.identity(4))).isTrue();
        assertThat(MatrixOps.isSymmetric(new int[][]{{42}})).isTrue();
        assertThat(MatrixOps.isSymmetric(new int[][]{{1, 2}, {3, 1}})).isFalse();
        assertThat(MatrixOps.isSymmetric(new int[][]{{1, 2, 3}, {2, 5, 6}, {3, 7, 9}})).isFalse();
    }

    @Test
    @DisplayName("Une matrice non carrée n'est pas symétrique")
    void nonSquareIsNotSymmetric() {
        assertThat(MatrixOps.isSymmetric(new int[][]{{1, 2, 3}, {2, 5, 6}})).isFalse();
    }
}
