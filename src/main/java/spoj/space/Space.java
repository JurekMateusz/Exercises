//https://pl.spoj.com/problems/JSPACE/
package  spoj.space;

import java.lang.*;
import java.util.Scanner;

public class Space {

    public String method(String[] txt) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < txt.length; i++) {
            if (txt[i] == null) {
                break;
            }
            if (txt[i].equals("")) {
                break;
            }
            boolean beforeCharSpace = false;

            for (int j = 0; j < txt[i].length(); j++) {
                char character = txt[i].charAt(j);
                if (character == ' ') {
                    beforeCharSpace = true;
                } else if (beforeCharSpace) {
                    int buf = character;
                    if (character >= 97 && character <= 122) {
                        buf -= 32;
                    }
                    str.append((char) buf);
                    beforeCharSpace = false;
                } else {
                    str.append(character);
                }
            }
            if ((i + 1) < txt.length && txt[i + 1] != null) {
                str.append(System.lineSeparator());
            }
        }
        return str.toString();
    }

    public static void main(String[] args) throws java.lang.Exception {
        Space main = new Space();
        String[] txt = new String[10];
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (true) {
            try {
                if (scanner.hasNextLine()) {
                    txt[i] = scanner.nextLine();
                    if (txt[i] == null || txt[i].equals("")) {
                        break;
                    }
                }
            } catch (java.lang.Throwable e) {
                throw new IllegalStateException(e);
            }
            i++;
        }
        System.out.print(main.method(txt));
    }
}

