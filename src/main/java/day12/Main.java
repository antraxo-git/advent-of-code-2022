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
            Character[][] grid1 = Files.lines(file.toPath())
                    .map(s -> Arrays.stream(s.split(""))
                            .map(s2 -> s2.charAt(0))
                            .toArray(Character[]::new))
                    .toArray(Character[][]::new);

            Character[][] grid2 = Files.lines(file.toPath())
                    .map(s -> Arrays.stream(s.split(""))
                            .map(s2 -> s2.charAt(0))
                            .toArray(Character[]::new))
                    .toArray(Character[][]::new);

            part1(grid1);
            part2(grid2);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void part2(Character[][] grid) {
        Position startingPoint = new Position(0,0, 0);

        for(int y=0; y < grid.length; y++) {
            for(int x=0; x < grid[0].length; x++) {
                if(grid[y][x] == 'E') {
                    grid[y][x] = 'z';
                    startingPoint.x = x;
                    startingPoint.y = y;
                }
            }
        }

        Deque<Position> posToCheck = new LinkedList<>();
        HashSet<Position> checkedPos = new HashSet<>();
        posToCheck.add(startingPoint);

        while(posToCheck.size() != 0) {
            Position pos = posToCheck.removeFirst();
            if(checkIfEnd2(pos, grid)) {
                System.out.println("Steps: " + pos.steps);
                break;
            }
            posToCheck.addAll(findNeighbors2(pos, grid, checkedPos));
        }
    }

    static void part1(Character[][] grid) {
        Position startingPoint = new Position(0,0, 0);
        Position endingPoint = new Position(0,0, 0);

        for(int y=0; y < grid.length; y++) {
            for(int x=0; x < grid[0].length; x++) {
                if(grid[y][x] == 'S') {
                    grid[y][x] = 'a';
                    startingPoint.x = x;
                    startingPoint.y = y;
                }
                if(grid[y][x] == 'E') {
                    grid[y][x] = 'z';
                    endingPoint.x = x;
                    endingPoint.y = y;
                }
            }
        }

        Deque<Position> posToCheck = new LinkedList<>();
        HashSet<Position> checkedPos = new HashSet<>();
        posToCheck.add(startingPoint);

        while(posToCheck.size() != 0) {
            Position pos = posToCheck.removeFirst();
            if(checkIfEnd(pos, endingPoint)) {
                System.out.println("Steps: " + pos.steps);
                break;
            }
            posToCheck.addAll(findNeighbors(pos, grid, checkedPos));
        }
    }

    static List<Position> findNeighbors2(Position point, Character[][] grid, HashSet<Position> checkedPos) {
        char posValue = grid[point.y][point.x];
        List<Position> neighbors = new ArrayList<>();
        if(point.getY() < grid.length - 1 ) {
            if(!(posValue - grid[point.y + 1][point.x] > 1)) {
                Position posToAdd = new Position(point.x, point.y + 1, point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }
        if(point.getX() < grid[0].length - 1) {
            if(!(posValue - grid[point.y][point.x + 1] > 1)) {
                Position posToAdd = new Position(point.x + 1, point.y, point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }
        if(point.getX() > 0) {
            if (!(posValue - grid[point.y][point.x - 1] > 1)) {
                Position posToAdd = new Position(point.x - 1, point.y , point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }
        if(point.getY() > 0) {
            if (!(posValue - grid[point.y - 1][point.x] > 1)) {
                Position posToAdd = new Position(point.x, point.y - 1, point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }

        return neighbors;
    }

    static boolean checkIfEnd2(Position point, Character[][] grid) {
        return grid[point.y][point.x] == 'a';
    }

    static List<Position> findNeighbors(Position point, Character[][] grid, HashSet<Position> checkedPos) {
        char posValue = grid[point.y][point.x];
        List<Position> neighbors = new ArrayList<>();
        if(point.getY() < grid.length - 1 ) {
            if(!(posValue - grid[point.y + 1][point.x] < -1)) {
                Position posToAdd = new Position(point.x, point.y + 1, point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }
        if(point.getX() < grid[0].length - 1) {
            if(!(posValue - grid[point.y][point.x + 1] < -1)) {
                Position posToAdd = new Position(point.x + 1, point.y, point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }
        if(point.getX() > 0) {
            if (!(posValue - grid[point.y][point.x - 1] < -1)) {
                Position posToAdd = new Position(point.x - 1, point.y , point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }
        if(point.getY() > 0) {
            if (!(posValue - grid[point.y - 1][point.x] < -1)) {
                Position posToAdd = new Position(point.x, point.y - 1, point.getSteps() + 1);
                if(!checkedPos.contains(posToAdd)) {
                    neighbors.add(posToAdd);
                    checkedPos.add(posToAdd);
                }
            }
        }

        return neighbors;
    }
    
    static boolean checkIfEnd(Position point, Position endingPoint) {
        return point.x == endingPoint.x && point.y == endingPoint.y;
    }
}
