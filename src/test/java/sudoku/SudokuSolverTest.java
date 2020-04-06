package sudoku;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {
    private int[][] sudProblem = {
            {7, 2, 0, 0, 6, 9, 0, 0, 4},
            {0, 0, 9, 0, 3, 0, 0, 5, 0},
            {8, 0, 0, 4, 0, 0, 2, 6, 0},

            {0, 6, 0, 9, 0, 0, 0, 0, 8},
            {0, 3, 0, 0, 8, 0, 0, 1, 0},
            {5, 0, 0, 0, 0, 2, 0, 4, 0},

            {0, 5, 1, 0, 0, 4, 0, 0, 6},
            {0, 8, 0, 0, 2, 0, 4, 0, 0},
            {4, 0, 0, 3, 7, 0, 0, 2, 5},
    };
    private int[][] sudSolve = {
            {7, 2, 5, 1, 6, 9, 3, 8, 4},
            {6, 4, 9, 2, 3, 8, 1, 5, 7},
            {8, 1, 3, 4, 5, 7, 2, 6, 9},

            {1, 6, 2, 9, 4, 3, 5, 7, 8},
            {9, 3, 4, 7, 8, 5, 6, 1, 2},
            {5, 7, 8, 6, 1, 2, 9, 4, 3},

            {2, 5, 1, 8, 9, 4, 7, 3, 6},
            {3, 8, 7, 5, 2, 6, 4, 9, 1},
            {4, 9, 6, 3, 7, 1, 8, 2, 5},
    };

    @Test
    void solveTest() {
        SudokuSolver sudokuSolver = new SudokuSolver();
        int[][] result = sudokuSolver.solve(sudProblem);
        boolean check = Arrays.deepEquals(result, sudSolve);
        assertTrue(check);
    }

    @Test
    void solveT() throws FileNotFoundException {
        SudokuSolver sudokuSolver = new SudokuSolver();
        Map<List<Integer>, List<Integer>> sudoku = getMapOfKeysAndSolutions(10);

        Iterator<Map.Entry<List<Integer>, List<Integer>>> iterator = sudoku.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<List<Integer>, List<Integer>> entry = iterator.next();
            List<Integer> result = sudokuSolver.solve(entry.getKey());
            boolean check = result.equals(entry.getValue());
            assertTrue(check);
        }
    }

    private Map<List<Integer>, List<Integer>> getMapOfKeysAndSolutions(int numOfSudoku) throws FileNotFoundException {
        File file = new File("D:\\PROJEKTY JAVA\\SudokuSolver\\src\\main\\resources\\sudoku.txt");
        Scanner scanner = new Scanner(file);
        Map<List<Integer>, List<Integer>> result = new HashMap<>();
        scanner.nextLine();
        while (numOfSudoku-- > 0) {
            String nextLine = scanner.nextLine();
            String[] splitTxt = nextLine.split(",");
            List<Integer> list = changeToList(splitTxt[0]);
            List<Integer> list1 = changeToList(splitTxt[0]);
            result.put(list, list1);
        }
        scanner.close();
        return result;
    }

    private List<Integer> changeToList(String text) {
        text = text.trim();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            char charBuf = text.charAt(i);
            int intBuf = Integer.valueOf(charBuf);
            result.add(result.size(), intBuf);
        }
        return result;
    }
}