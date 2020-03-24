//https://pl.spoj.com/problems/STOS/
package spoj.stack;

import java.util.LinkedList;
import java.util.Scanner;

public class Stack {
    private static int[] stackTab = new int[10];
    private static int tip = -1;
    private static LinkedList<String> resoult = new LinkedList<>();

    public boolean notFull() {
        return tip < stackTab.length - 1;
    }

    public boolean notEmpty() {
        return tip >= 0;
    }

    public void pop() {
        if (notEmpty()) {
            resoult.add(String.valueOf(stackTab[tip]));
            stackTab[tip] = 0;
            tip--;
        } else {
            resoult.add(":(");
        }
    }

    public void addElement(int x) {
        if (notFull()) {
            tip++;
            stackTab[tip] = x;
            resoult.add(":)");
        } else {
            resoult.add(":(");
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        Stack stack = new Stack();
        boolean run = true;
        Scanner scanner = new Scanner(System.in);
        String buf;
        int buff;
        try {
            while (run) {
                buf = scanner.nextLine();
                if (buf.equals("")) {
                    run = false;
                    break;
                }
                switch (buf.charAt(0)) {
                    case '+':
                        stack.addElement(scanner.nextInt());
                        if(scanner.hasNext()){
                            scanner.nextLine();
                        } //if statemend needed ,otherwise NZEC on spoj
                        break;
                    case '-':
                        stack.pop();
                        break;
                    default:
                        throw new IllegalArgumentException("ERR");
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("err");
        }
        for (String n : resoult) {
            System.out.println(n);
        }
    }
}


