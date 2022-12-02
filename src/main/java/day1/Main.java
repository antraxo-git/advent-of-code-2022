package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/day1/input.txt");

        List<Integer> input = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            int sum = 0;
            while (line != null) {
                if(!line.isBlank()) {
                    sum += Integer.parseInt(line);
                } else {
                    input.add(sum);
                    sum = 0;
                }
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //part1
        System.out.println(input.stream()
                .max(Comparator.comparingInt(i -> i))
                .get());
        //part2
        System.out.println(input.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum));
    }
}
