package day12;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/day12/input.txt");

        try {
            Character[][] grid = Files.lines(file.toPath())
                    .map(s -> Arrays.stream(s.split(""))
                            .map(s2 -> s2.charAt(0))
                            .toArray(Character[]::new))
                    .toArray(Character[][]::new);

            for(Character[] add : grid) {
                System.out.println(Arrays.toString(add));
            }

            Deque<Position> posToCheck = new LinkedList<>();
            List<Position> checkedPos = new ArrayList<>();
            Position startingPoint = new Position(0,0, 0);
            posToCheck.add(startingPoint);

            while(posToCheck.size() != 0) {
                Position pos = posToCheck.getFirst();
                posToCheck.removeFirst();
                if(checkIfEnd(pos, grid)) {
                    System.out.println("Steps: " + pos.steps);
                    break;
                }
                checkedPos.add(pos);
                posToCheck.addAll(findNeighbors(pos, grid, checkedPos));
                System.out.println(posToCheck.size());
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static List<Position> findNeighbors(Position point, Character[][] grid, List<Position> checkedPos) {
        char posValue = grid[point.x][point.y];
        if(posValue == 'S') {
            posValue = 'a';
        }
        List<Position> neighbors = new ArrayList<>();
        if(point.getX() < grid.length - 1 ) {
            if(!(posValue - grid[point.x + 1][point.y] < -1)) {
                neighbors.add(new Position(point.x + 1, point.y, point.getSteps() + 1));
            }
        }
        if(point.getY() < grid.length - 1) {
            if(!(posValue - grid[point.x][point.y + 1] < -1)) {
                neighbors.add(new Position(point.x, point.y + 1, point.getSteps() + 1));
            }
        }
        if(point.getY() > 0) {
            if (!(posValue - grid[point.x][point.y - 1] < -1)) {
                neighbors.add(new Position(point.x, point.y - 1, point.getSteps() + 1));
            }
        }
        if(point.getX() > 0) {
            if (!(posValue - grid[point.x - 1][point.y] < -1)) {
                neighbors.add(new Position(point.x - 1, point.y, point.getSteps() + 1));
            }
        }
        return neighbors.stream().filter(n -> !checkedPos.contains(n)).collect(Collectors.toList());
    }
    
    static boolean checkIfEnd(Position point, Character[][] grid) {
        return grid[point.x][point.y].equals('E');
    }
}
