package jetbrainsacademy.sudokucheck;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuVerification {
    private int sizeBox;
    private  List<Integer> setAllNumbers;

    public SudokuVerification(int sizeBox) {
        this.sizeBox = sizeBox;
        setAllNumbers = IntStream
                .rangeClosed(1, sizeBox*sizeBox)
                .boxed()
                .collect(Collectors.toList());
    }

    public boolean isSolve(int[][] matrix) {
        for (int i = 0; i < matrix.length; i += sizeBox) {
            for (int j = 0; j < matrix.length; j += sizeBox) {
                if (isNotSquareCorrect(matrix, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNotSquareCorrect(int[][] matrix, int x, int y) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = x; i < x + sizeBox; i++) {
            for (int j = y; j < y + sizeBox; j++) {
                int number = matrix[i][j];
                if (isNumberRepeatInSquare(matrix, x, y, number)) {
                    return true;
                }
                if (isNumberRepeatInColumn(matrix, j, number)) {
                    return true;
                }
                if (isNumberRepeatInRow(matrix, i, number)) {
                    return true;
                }
                set.add(number);
            }
        }
        return !set.containsAll(setAllNumbers);
    }

    private boolean isNumberRepeatInSquare(int[][] matrix, int x, int y, int number) {
        int buf = 0;
        for (int i = x; i < x + sizeBox; i++) {
            for (int j = y; j < y + sizeBox; j++) {
                if (number == matrix[i][j]) {
                    buf++;
                }
            }
        }
        return buf != 1;
    }

    private boolean isNumberRepeatInColumn(int[][] matrix, int y, int number) {
        int buf = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (number == matrix[i][y]) {
                buf++;
            }
        }
        return buf != 1;
    }

    private boolean isNumberRepeatInRow(int[][] matrix, int x, int number) {
        int buf = 0;
        for (int i = 0; i < matrix.length; i++) {
            if (number == matrix[x][i]) {
                buf++;
            }
        }
        return buf != 1;
    }

}