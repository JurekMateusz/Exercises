package exce.serialization;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleSerialization {
    private void simpleSerialization() throws IOException, ClassNotFoundException {
        ArrayList<String> arrayList = readInput();
        arrayList.forEach(a -> System.out.println(a));
        serialization(arrayList);
        arrayList = null;
        arrayList = deserialization();
        arrayList.forEach(a -> System.out.println(a));
    }

    private ArrayList<String> readInput() {
        ArrayList<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("")) {
                break;
            }
            result.add(input);
        }
        return result;
    }

    private void serialization(ArrayList<String> arrayList) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("D:\\PROJEKTY JAVA\\EXCERCISES\\src\\main\\resources\\objects.bin"))) {

            objectOutputStream.writeObject(arrayList);
        }
    }

    private ArrayList<String> deserialization() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("D:\\PROJEKTY JAVA\\EXCERCISES\\src\\main\\resources\\objects.bin"))) {

            return (ArrayList<String>) objectInputStream.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new SimpleSerialization().simpleSerialization();
    }
}
