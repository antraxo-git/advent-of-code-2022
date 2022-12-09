package day9;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        File file = new File("src/main/java/day9/input.txt");

        try {
            String[][] moves = Files.lines(file.toPath())
                    .map(s -> s.split(" "))
                    .toArray(String[][]::new);

            part1(moves);
            part2(moves);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void part1(String[][] moves) {
        Set<Point> visitedPoints = new HashSet<>();
        Knot head = new Knot(new Coordinate(0, 0));
        Knot tail = new Knot(new Coordinate(0, 0));
        visitedPoints.add(new Point(tail.getPosition().getX(), tail.getPosition().getY()));

        for (String[] move : moves) {
            doMove(move, head, tail, visitedPoints);
        }

        System.out.println(visitedPoints.size());
    }

    static void part2(String[][] moves) {
        Set<Point> visitedPoints = new HashSet<>();
        Knot[] knots = new Knot[10];
        for (int i = 0; i < knots.length; i++) {
            knots[i] = new Knot(new Coordinate(11, -15));
        }
        visitedPoints.add(new Point(knots[knots.length - 1].getPosition().getX(), knots[knots.length - 1].getPosition().getY()));

        for (String[] move : moves) {
            for (int j = 0; j < Integer.parseInt(move[1]); j++) {
                for (int i = 0; i < knots.length; i++) {
                    if (i == 0) {
                        moveHead(move[0], knots[0]);
                    } else {
                        Knot head = knots[i - 1];
                        Knot tail = knots[i];
                        if (!head.isAdjacent(tail)) {
                            tail.getToHead(head);

                        }
                    }
                }
                visitedPoints.add(new Point(knots[knots.length - 1].getPosition().getX(), knots[knots.length - 1].getPosition().getY()));
            }
            //printRope(knots);
        }

        System.out.println(visitedPoints.size());
    }

    static void moveHead(String direction, Knot head) {
        switch (direction) {
            case "U" -> head.incrementY();
            case "D" -> head.decrementY();
            case "R" -> head.incrementX();
            case "L" -> head.decrementX();
        }

    }

    static void doMove(String[] move, Knot head, Knot tail, Set<Point> visitedPoints) {
        String direction = move[0];
        int distance = Integer.parseInt(move[1]);
        for (int i = 0; i < distance; i++) {
            switch (direction) {
                case "U":
                    head.incrementY();
                    if (!head.isAdjacent(tail)) {
                        tail.getToHead(head);
                    }
                    break;
                case "D":
                    head.decrementY();
                    if (!head.isAdjacent(tail)) {
                        tail.getToHead(head);
                    }
                    break;
                case "R":
                    head.incrementX();
                    if (!head.isAdjacent(tail)) {
                        tail.getToHead(head);
                    }
                    break;
                case "L":
                    head.decrementX();
                    if (!head.isAdjacent(tail)) {
                        tail.getToHead(head);
                    }
                    break;
            }
            visitedPoints.add(new Point(tail.getPosition().getX(), tail.getPosition().getY()));
        }
    }

    static void printRope(Knot[] knots) {
        String[][] rope = new String[30][30];
        for (String[] line : rope) {
            Arrays.fill(line, ".");
        }
        for (int i = 0; i < knots.length; i++) {
            Knot k = knots[i];
            if (i == 0) {
                rope[-k.getPosition().getY()][k.getPosition().getX()] = "H";
            } else {
                rope[-k.getPosition().getY()][k.getPosition().getX()] = String.valueOf(i);
            }
        }
        for (String[] lines : rope) {
            for (String s : lines) {
                System.out.print(s);
            }
            System.out.println();
        }
        System.out.println();

    }
}
