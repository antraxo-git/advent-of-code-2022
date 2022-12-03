package day3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Part2 {
    public static void main(String[] args) {
        File file = new File("src/main/java/day3/input.txt");

        List<List<String>> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int elf = 3;
            int group = 0;
            while (line != null) {
                input.add(new ArrayList<>());
                while (elf > 0) {
                    input.get(group).add(line);
                    line = reader.readLine();
                    elf--;
                }
                elf = 3;
                group++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        part2(input);
    }

    static void part2(List<List<String>> input) {
        int sumOfPriorities = 0;
        for (List<String> group : input) {
            sumOfPriorities += searchRucksack(group, new HashSet<>(), 0);
        }
        System.out.println(sumOfPriorities);
    }

    static int searchRucksack(List<String> group, Set<Character> items, int index) {
        if(index == 0) {
            for (char c : group.get(index).toCharArray()) {
                items.add(c);
            }
            return searchRucksack(group, items, ++index);
        }

        if(index == 1) {
            HashSet<Character> items2 = new HashSet<>();
            for (char c : group.get(index).toCharArray()) {
                if (items.contains(c)) {
                    items2.add(c);
                }
            }
            return searchRucksack(group, items2, ++index);
        }

        if(index == 2) {
            for (char c : group.get(index).toCharArray()) {
                if (items.contains(c)) {
                    return getPriority(c);
                }
            }
        }

        return 0;
    }

    static int getPriority(char item) {
        return item > 96 ? item - 96 : item - 38;
    }
}
