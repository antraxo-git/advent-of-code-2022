//package day2;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class Main {
//
//    public static void main(String[] args) {
//        File file = new File("src/main/java/day2/input.txt");
//
//        List<String> input = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//            String line = reader.readLine();
//            while (line != null) {
//                input.add(line);
//                line = reader.readLine();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    static void part1(List<String> input) {
//        Map<Character, String> shapes = Map.of(
//                'A', "Rock",
//                'B', "Paper",
//                'C', "Scissors",
//                'X', "Rock",
//                'Y', "Paper",
//                'Z', "Scissors"
//        );
//
//        //opp Rock: -1 - win, 0 - draw, -2 - lose | opp Paper: 1 - lose, 0 - draw, -1 - win | opp Scissors 2 - win, 1 - lose, 0 - draw
//
//        Map<String, Integer> shapesPoints = Map.of(
//                "Rock", 1,
//                "Paper", 2,
//                "Scissors", 3
//        );
//
//        Map<String, Integer> resultPoints = Map.of(
//                "win", 6,
//                "lose", 0,
//                "draw", 3
//        );
//
//        int sumOfPoints = 0;
//
//        for (String s : input) {
//            int opp = shapesPoints.get(shapes.get(s.charAt(0)));
//            int me = shapesPoints.get(shapes.get(s.charAt(2)));
//
//            int result = opp - me;
//            int modulo = opp % me;
//            System.out.println(modulo);
//            switch (result) {
//                case -2, 1 -> sumOfPoints += me + resultPoints.get("lose");
//                case -1, 2 -> sumOfPoints += me + resultPoints.get("win");
//                case 0 -> sumOfPoints += me + resultPoints.get("draw");
//            }
//        }
//
//        System.out.println(sumOfPoints);
//    }
//
//    static void part2(List<String> input) {
//
//        Map<Character, Character> wins = Map.of(
//                'A', 'B',
//                'B', 'C',
//                'C', 'A'
//        );
//
//        Map<Character, Character> loses = Map.of(
//                'A', 'C',
//                'B', 'A',
//                'C', 'B'
//        );
//
//        Map<Character, Integer> shapesPoints = Map.of(
//                'A', 1,
//                'B', 2,
//                'C', 3
//        );
//
//        int sumOfPoints = 0;
//
//        for (String s : input) {
//            char opp = s.charAt(0);
//            char result = s.charAt(2);
//
//            switch (result) {
//                case 'X' -> sumOfPoints += shapesPoints.get(loses.get(opp));
//                case 'Y' -> sumOfPoints += shapesPoints.get(opp) + 3;
//                case 'Z' -> sumOfPoints += shapesPoints.get(wins.get(opp)) + 6;
//            }
//        }
//        System.out.println(sumOfPoints);
//    }
//}
