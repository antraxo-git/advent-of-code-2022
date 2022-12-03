package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        File file = new File("src/main/java/day3/input.txt");

        List<List<String>> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                final int mid = line.length() / 2;
                input.add(Arrays.asList(line.substring(0, mid), line.substring(mid)));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        part1(input);
    }

    static void part1(List<List<String>> input) {
        int sumOfPriorities = 0;
        for(List<String> compartments : input) {
            HashSet<Character> firstHalfItems = new HashSet<>();
            String firstHalf = compartments.get(0);
            String secondHalf = compartments.get(1);

            for(char item : firstHalf.toCharArray()) {
                firstHalfItems.add(item);
            }
            for(char item : secondHalf.toCharArray()) {
                if(firstHalfItems.contains(item)) {
                    sumOfPriorities += getPriority(item);
                    break;
                }
            }
        }

        System.out.println(sumOfPriorities);
    }

    static int getPriority(char item) {
        return item > 96 ? item - 96 : item - 38;
    }
}
