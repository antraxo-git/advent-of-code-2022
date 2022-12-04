package day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Range;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/day4/input.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Range[]> rangesPairs = reader.lines().map(s -> s.split("[,-]"))
                    .map(arr -> {
                        Range<Integer> range1 = Range.between(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
                        Range<Integer> range2 = Range.between(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
                        return new Range[]{range1, range2};
                    })
                    .collect(Collectors.toList());
            //part1
            System.out.println(rangesPairs.stream()
                    .filter(ranges -> ranges[0].containsRange(ranges[1]) || ranges[1].containsRange(ranges[0]))
                    .count());
            //part2
            System.out.println(rangesPairs.stream()
                    .filter(ranges -> ranges[0].isOverlappedBy(ranges[1]) || ranges[1].isOverlappedBy(ranges[0]))
                    .count());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
