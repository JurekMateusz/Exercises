//https://pl.spoj.com/problems/SKARBFI/

package spoj.skarbfinder;

import java.awt.*;
import java.util.LinkedList;
import java.util.Scanner;

public class SkarbFinder {
    private static Point point;
    private static Point oldPoint;
    private static LinkedList<String> result;
    Scanner myReader;
    private int[] directions; //NORTH ,SOUT,EAST,WEST

    public SkarbFinder() {
        result = new LinkedList<>();
        point = new Point();
        oldPoint = new Point(0, 0);
        myReader = new Scanner(System.in);
    }

    public void fun() {
        int numbersOfSeries = myReader.nextInt();

        for (int i = 0; i < numbersOfSeries; i++) {
            directions = new int[]{0, 0, 0, 0};
            readSerie();

            oldPoint.setLocation(point.getX(), point.getY());
            double newX = point.getX() + (directions[2] + directions[3]);
            double newY = point.getY() + (directions[0] + directions[1]);
            point.setLocation(newX, newY);

            if (oldPoint.equals(point)) {
                result.add("studnia");
                continue;
            }
            countSteps();
        }
        myReader.close();

        for (String n : result) {
            System.out.println(n);
        }
    }

    private void readSerie() {
        int numbersOfPairs = myReader.nextInt();
        for (int j = 0; j < numbersOfPairs; j++) {
            int direction = myReader.nextInt();
            int numberOfSteps = myReader.nextInt();
            addDir(direction, numberOfSteps);
        }
    }

    private void addDir(int direction, int numberOfSteps) {
        switch (direction) {
            case 0:
                directions[0] += numberOfSteps;
                break;
            case 1:
                directions[1] -= numberOfSteps;
                break;
            case 2:
                directions[3] -= numberOfSteps;
                break;
            case 3:
                directions[2] += numberOfSteps;
                break;
            default:
                System.err.println("not  allowed number");
        }

    }

    private void countSteps() {
        if (point.getY() != oldPoint.getY()) {
            countYYSteps();
        }
        if (point.getX() != oldPoint.getX()) {
            countXXSteps();
        }
    }

    private void countYYSteps() {
        int count = (int) (point.getY() - oldPoint.getY());
        if (point.getY() > oldPoint.getY()) {
            result.add("0 " + count);
        } else {
            result.add("1 " + Math.abs(count));
        }
    }

    private void countXXSteps() {
        int count = (int) (point.getX() - oldPoint.getX());
        if (point.getX() > oldPoint.getX()) {
            result.add("3 " + count);
        } else {
            result.add("2 " + Math.abs(count));
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        new SkarbFinder().fun();
    }
}

