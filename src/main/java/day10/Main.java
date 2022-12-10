package day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/day10/input.txt");

        try {
            List<String> instructions = Files.readAllLines(file.toPath());

            part1(instructions);
            part2(instructions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void part2(List<String> instructions) {
        int X = 1;
        int cycleInRow = 0;

        for(String instruction : instructions) {
            if(instruction.equals("noop")) {

                printPixel(cycleInRow, X);
                cycleInRow++;
                if(cycleInRow == 40) {
                    cycleInRow = 0;
                    System.out.println();
                }
            } else {
                int addx = Integer.parseInt(instruction.substring(5));

                printPixel(cycleInRow, X);
                cycleInRow++;
                if(cycleInRow == 40) {
                    cycleInRow = 0;
                    System.out.println();
                }

                printPixel(cycleInRow, X);
                cycleInRow++;
                if(cycleInRow == 40) {
                    cycleInRow = 0;
                    System.out.println();
                }

                X+= addx;
            }
        }
    }

    static void printPixel(int cycleInRow, int X) {
        if(Math.abs(cycleInRow - X) < 2) {
            System.out.print("#");
        } else {
            System.out.print(".");
        }
    }

    static void part1(List<String> instructions) {
        int signalStrengthSum = 0;
        int cycle = 0;
        int X = 1;

        for(String instruction : instructions) {
            if(instruction.equals("noop")) {
                cycle++;
                if(checkIfInteresting(cycle)) {
                    signalStrengthSum+= cycle * X;
                }
            } else {
                int addx = Integer.parseInt(instruction.substring(5));
                cycle++;
                if(checkIfInteresting(cycle)) {
                    signalStrengthSum+= cycle * X;
                }
                cycle++;
                if(checkIfInteresting(cycle)) {
                    signalStrengthSum+= cycle * X;
                }
                X+= addx;
            }
        }
        System.out.println(signalStrengthSum);
    }

    static boolean checkIfInteresting(int cycle) {
        return cycle == 20 || cycle == 60 || cycle == 100
                || cycle == 140 || cycle == 180 || cycle == 220;
    }
}
