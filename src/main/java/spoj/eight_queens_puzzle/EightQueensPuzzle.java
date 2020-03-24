//https://pl.spoj.com/problems/FR_10_06/
package spoj.eight_queens_puzzle;

import java.util.Scanner;

public class EightQueensPuzzle {
    public static boolean check(int[] tab, int i, int xx) {
        int yy = tab[i];
        int yyy = tab[i];
        while (--xx > 0) {
            if (tab[xx] == tab[i] || tab[xx] == --yy || tab[xx] == ++yyy) {
                return false;
            }
        }
        xx = i;
        yy = tab[i];
        yyy = tab[i];
        while (++xx < tab.length) {
            if (tab[xx] == tab[i] || tab[xx] == ++yy || tab[xx] == --yyy) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int[] tab = new int[8];
        int x = scanner.nextInt();
        for (int k = 0; k < x; k++) {
            for (int j = 0; j < 8; j++) {
                tab[j] = scanner.nextInt();
            }
            boolean correct = true;
            for (int i = 0; i < tab.length; i++) {
                correct = check(tab, i, i);
                if (!correct) {
                    break;
                }
            }
            if (correct) {
                System.out.println("TAK");
            } else {
                System.out.println("NIE");
            }
        }
    }
}


