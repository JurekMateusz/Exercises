package exce.serialization;

import java.io.*;
import java.util.Calendar;

public class Human implements Serializable {
    private String name;
    private transient int age;

    Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int temp = stream.readInt();
        age = Calendar.getInstance().get(Calendar.YEAR) - temp;
    }

    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeInt(Calendar.getInstance().get(Calendar.YEAR) - age);
    }

    @Override
    public String toString() {
        return "Name: " + name + ",age:" + age;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Human human = new Human("mat", 21);
        System.out.println(human);
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(
                "D:\\PROJEKTY JAVA\\EXCERCISES\\src\\main\\resources\\obj.bin"))) {
            outputStream.writeObject(human);
        }
        human = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(
                "D:\\PROJEKTY JAVA\\EXCERCISES\\src\\main\\resources\\obj.bin"))) {
            human = (Human) inputStream.readObject();
        }
        System.out.println(human);
    }
}
