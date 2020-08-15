package jetbrainsacademy.sudokucheck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuVerificationTest {

    @Test
    void is_solve_1() {
        int[][] matrix = {{1}};
        SudokuVerification sudoku = new SudokuVerification(1);
        assertTrue(sudoku.isSolve(matrix));
    }

    @Test
    void is_solve_2() {
        int[][] matrix = {
                {5, 8, 9, 6, 7, 4, 2, 1, 3},
                {7, 4, 3, 1, 8, 2, 9, 5, 6},
                {1, 2, 6, 9, 5, 3, 8, 7, 4},
                {9, 3, 5, 4, 2, 1, 7, 6, 8},
                {4, 1, 2, 8, 6, 7, 3, 9, 5},
                {6, 7, 8, 3, 9, 5, 1, 4, 2},
                {8, 6, 4, 2, 1, 9, 5, 3, 7},
                {3, 9, 7, 5, 4, 8, 6, 2, 1},
                {2, 5, 1, 7, 3, 6, 4, 8, 9},
        };
        SudokuVerification sudoku = new SudokuVerification(3);
        assertTrue(sudoku.isSolve(matrix));
    }

    @Test
    void is_solve_3() {
        int[][] matrix = {
                {1, 1, 2, 2},
                {1, 1, 2, 2},
                {3, 3, 4, 4},
                {3, 3, 4, 4},
        };
        SudokuVerification sudoku = new SudokuVerification(2);
        assertFalse(sudoku.isSolve(matrix));
    }
}