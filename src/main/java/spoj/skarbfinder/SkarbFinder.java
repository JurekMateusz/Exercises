//https://pl.spoj.com/problems/SKARBFI/

package  spoj.skarbfinder;

import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;

public class SkarbFinder {
    private static Point point;
    private static Point oldPoint;
    private static LinkedList<String> resoult;
    private static long NORTH = 0;
    private static long SOUT = 0;
    private static long EAST = 0;
    private static long WEST = 0;

    public static void fun(){
        resoult = new LinkedList<>();
        point = new Point();
        oldPoint = new Point(0, 0);

        Scanner myReader = new Scanner(System.in);

        int numbersOfSeries = myReader.nextInt();
        for (int i = 0; i < numbersOfSeries; i++) {
            int numbersOfPairs = myReader.nextInt();
            myReader.nextLine();
            for (int j = 0; j < numbersOfPairs; j++) {
                String line = myReader.nextLine();
                String[] splitLine = line.split(" ");
                int dat1 = Integer.parseInt(splitLine[0]);
                long dat2 = Integer.parseInt(splitLine[1]);
                switch (dat1) {
                    case 0:
                        //NORTH
                        NORTH += dat2;
                        break;
                    case 1:
                        //SOUT
                        SOUT -= dat2;
                        break;
                    case 2:
                        //WEST
                        WEST -= dat2;
                        break;
                    case 3:
                        //EAST
                        EAST += dat2;
                        break;
                    default:
                        System.err.println("not  allowed number");
                }
            }
            oldPoint.setLocation(point.getX(), point.getY());
            point.setLocation(point.getX() +(EAST + WEST), point.getY()+(NORTH + SOUT));

            if (oldPoint.equals(point)) {
                resoult.add("studnia");
                continue;
            }
            if (point.getY() != oldPoint.getY()) {
                int count = 0;
                if (point.getY() > oldPoint.getY()) {
                    while (count != point.getY()) {
                        count++;
                    }
                    resoult.add("0 " + (count - (int) oldPoint.getY()));
                } else {
                    while (count != point.getY()) {
                        count--;
                    }
                    resoult.add("1 " + Math.abs(count + (int) oldPoint.getY()));
                }
            }
            if (point.getX() != oldPoint.getX()) {
                int count = 0;
                if (point.getX() > oldPoint.getX()) {
                    while (count != point.getX()) {
                        count++;
                    }
                    resoult.add("3 " + (count - (int) oldPoint.getX()));
                } else {
                    while (count != point.getX()) {
                        count--;
                    }
                    resoult.add("2 " + Math.abs(count + (int) oldPoint.getX()));
                }
            }
            WEST = 0;
            NORTH = 0;
            SOUT = 0;
            EAST = 0;
        }
        myReader.close();
        for (String n : resoult) {
            System.out.println(n);
        }
    }
    public static void main(String[] args) throws java.lang.Exception{
        fun();
    }

}
