package exce.findword;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindWord {
    private final int MAX_SEARCH_STRINGS;
    private final String searchString;

    FindWord(int maxSearchStrings, String searchString) {
        this.MAX_SEARCH_STRINGS = maxSearchStrings;
        this.searchString = searchString;
    }

    public void findWords1(String fileName) throws IOException {
        List<String> listFile = readFile(fileName);
        ArrayList<Integer> keys = countKeysInRows(listFile);
        printResult(keys);
    }

    private List<String> readFile(String fileName) throws IOException {
        List<String> result;
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            result = lines.collect(Collectors.toList());
        }
        return result;
    }

    private ArrayList<Integer> countKeysInRows(List<String> listFile) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < listFile.size(); i++) {
            String txt = listFile.get(i);
            int numbers = 0;
            while (true) {
                int index = txt.indexOf(searchString);
                if (index == -1) {
                    break;
                }
                txt = txt.substring(index + searchString.length());
                numbers++;
            }
            result.add(numbers);
        }
        return result;
    }

    private void printResult(ArrayList<Integer> keys) {
        int numberOfLine = 0;
        int sum = 0;
        for (int i = keys.size() - 1; i > 0; i--) {
            sum += keys.get(i);
            if (sum > MAX_SEARCH_STRINGS) {
                numberOfLine = i;
                break;
            }
        }
        for (int i = numberOfLine; i < keys.size(); i++) {
            System.out.println("Line number: " + (i + 1) + " Count: " + keys.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        FindWord findWord = new FindWord(10, "test");
        findWord.findWords1("D:\\PROJEKTY JAVA\\EXCERCISES\\src\\main\\resources\\file.txt");
    }
}