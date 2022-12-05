package day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> stacksInputLines = new ArrayList<>();
        List<String> moves = new ArrayList<>();

        File file = new File("src/main/java/day5/input.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                if (stacksInputLines.size() < 8) {
                    stacksInputLines.add(line);
                }
                if (line.startsWith("move")) {
                    moves.add(line);
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int longestLine = stacksInputLines.stream()
                .map(s -> s.length())
                .max(Comparator.comparingInt(i1 -> i1))
                .get();

        List<Stack<String>> stacks = new ArrayList<>();

        for (int i = 1; i < longestLine; i += 4) {
            Stack<String> stackCrates = new Stack<>();
            for (String s : stacksInputLines) {
                if (i < s.length() && Character.isLetter(s.charAt(i)))
                    stackCrates.add(String.valueOf(s.charAt(i)));
            }
            Collections.reverse(stackCrates);
            stacks.add(stackCrates);
        }

        List<String> parsedMoves = moves.stream()
                .map(s -> s.replace("move ", "").replace(" from ", "").replace(" to ", ""))
                .collect(Collectors.toList());

        part1(stacks, parsedMoves);
    }

    static void part1(List<Stack<String>> stacks, List<String> moves) {
        for (String move : moves) {
            int countOfCrates;
            int stackFrom;
            int stackTo;
            if (move.length() == 4) {
                countOfCrates = Integer.parseInt(move.substring(0, 2));
                stackFrom = Character.getNumericValue(move.charAt(2)) - 1;
                stackTo = Character.getNumericValue(move.charAt(3)) - 1;
            } else {
                countOfCrates = Character.getNumericValue(move.charAt(0));
                stackFrom = Character.getNumericValue(move.charAt(1)) - 1;
                stackTo = Character.getNumericValue(move.charAt(2)) - 1;
            }
            List<String> transfer = new ArrayList<>();

            for (int i = 0; i < countOfCrates; i++) {
                transfer.add(stacks.get(stackFrom).pop());
            }

//            Part2
//            Collections.reverse(transfer);

            stacks.get(stackTo).addAll(transfer);
        }

        for (Stack<String> stack : stacks) {
            System.out.print(stack.get(stack.size() - 1));
        }
    }
}
