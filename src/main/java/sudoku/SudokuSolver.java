package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SudokuSolver {
    public List<Integer> solve(List<Integer> problem) {
        int[][] problemTab = changeToTab(problem);
        int[][] solution = this.solve(problemTab);
        List<Integer> result = changeToList(solution);
        return result;
    }

    private int[][] changeToTab(List<Integer> listToChange) {
        int[][] result = new int[9][9];
        int k = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length; j++) {
                result[i][j] = listToChange.get(k);
                k++;
            }
        }
        return result;
    }

    private List<Integer> changeToList(int[][] tableToChange) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < tableToChange.length; i++) {
            for (int j = 0; j < tableToChange.length; j++) {
                result.add(result.size(),tableToChange[i][j]);
            }
        }
        return result;
    }

    public int[][] solve(int[][] problem) {
        List[][] possibilities = create();

        while (anyEmpty(problem)) {
            for (int i = 0; i < problem.length; i += 3) {
                for (int j = 0; j < problem.length; j += 3) {
                    solveOneSquare(problem, possibilities, i, j);
                }
            }
        }
        return problem;
    }

    private List[][] create() {
        List<Integer>[][] result = new List[9][9];
        initiate(result);
        return result;
    }

    private void initiate(List[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i][j] = Stream.iterate(1, n -> n + 1)
                        .limit(9)
                        .collect(Collectors.toList());
            }
        }
    }

    private boolean anyEmpty(int[][] list) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                if (list[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    private void solveOneSquare(int[][] problem, List[][] possibilities, int x, int y) {
        eliminateNumExistingInSquare(problem, possibilities, x, y);
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (problem[i][j] != 0) {
                    continue;
                }
                eliminateNumExistingInColumnAndRows(problem, possibilities, i, j);
                addToResult(problem, possibilities, i, j);
            }
        }
    }

    private void eliminateNumExistingInSquare(int[][] problem, List[][] possibilities, int x, int y) {
        int[] duplicateNumbers = numberOfNumbersInSquare(problem, x, y);
        for (int i = 1; i < duplicateNumbers.length; i++) {
            if (duplicateNumbers[i] != 0) {
                eliminateFromPossibilities(possibilities, x, y, i);
            }
        }
    }

    private int[] numberOfNumbersInSquare(int[][] problem, int x, int y) {
        int[] result = new int[10];
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                if (problem[i][j] == 0) {
                    continue;
                }
                result[problem[i][j]]++;
            }
        }
        return result;
    }

    private void eliminateFromPossibilities(List[][] possibilities, int x, int y, int ii) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                possibilities[i][j].remove(new Integer(ii));
            }
        }
    }

    private void eliminateNumExistingInColumnAndRows(int[][] problem, List[][] possibilities, int x, int y) {
        for (int i = 0; i < problem.length; i++) {
            eliminateInColumn(problem, possibilities, x, y, i);
            eliminateInRows(problem, possibilities, x, y, i);
        }
    }

    private void eliminateInColumn(int[][] problem, List[][] possibilities, int x, int y, int i) {
        int buf = problem[i][y];
        if (buf != 0 && possibilities[x][y].indexOf(buf) != -1) {
            possibilities[x][y].remove(new Integer(buf));
        }
    }


    private void eliminateInRows(int[][] problem, List[][] possibilities, int x, int y, int i) {
        int buf = problem[x][i];
        if (buf != 0 && possibilities[x][y].indexOf(buf) != -1) {
            possibilities[x][y].remove(new Integer(buf));
        }
    }

    private void addToResult(int[][] problem, List[][] possibilities, int i, int j) {
        if (problem[i][j] == 0 && possibilities[i][j].size() == 1) {
            problem[i][j] = (int) possibilities[i][j].get(0);
            possibilities[i][j].clear();
        }
    }
}