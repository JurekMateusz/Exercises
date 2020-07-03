package jetbrainsacademy.sudokucheck;

import java.util.*;

/**
 * Dayly
 * Multi-dimensional array -> Check sudoku
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sizeBox = scanner.nextInt();
        int sizeMatrix = (int) Math.pow(sizeBox, 2);
        int[][] matrix = new int[sizeMatrix][sizeMatrix];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        SudokuVerification sudoku = new SudokuVerification(sizeBox);

        System.out.println(sudoku.isSolve(matrix) ? "YES" : "NO");

    }
}