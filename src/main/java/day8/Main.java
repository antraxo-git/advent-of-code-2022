package day8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/day8/input.txt");

        try {
            Integer[][] trees = Files.lines(file.toPath())
                    .map(s -> s.split(""))
                    .map(a -> Arrays.stream(a)
                            .map(Integer::parseInt)
                            .toArray(Integer[]::new))
                    .toArray(Integer[][]::new);

            part1(trees);
            part2(trees);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void part1(Integer[][] trees) {
        int sumOfVisibleTrees = 0;

        //rows
        for (int i = 1; i < trees.length - 1; i++) {
            //columns
            for (int j = 1; j < trees[i].length - 1; j++) {
                Integer tree = trees[i][j];
                int invisible = 0;
                //check row left
                for (int k = j - 1; k>=0; k--) {
                    if (trees[i][k] >= tree) {
                        invisible++;
                        break;
                    }
                }
                //check row right
                for (int k = j + 1; k < trees[i].length; k++) {
                    if (trees[i][k] >= tree) {
                        invisible++;
                        break;
                    }
                }
                //check column up
                for (int k = i - 1; k>=0; k--) {
                    if (trees[k][j] >= tree) {
                        invisible++;
                        break;
                    }
                }
                //check column down
                for (int k = i + 1; k < trees.length; k++) {
                    if (trees[k][j] >= tree) {
                        invisible++;
                        break;
                    }
                }
                if(invisible < 4) {
                    sumOfVisibleTrees++;
                }
            }
        }

        System.out.println((sumOfVisibleTrees + trees.length * 2 + trees[0].length * 2) - 4);
    }

    static void part2(Integer[][] trees) {


        int highestScenicScore = 0;
        //rows
        for (int i = 1; i < trees.length - 1; i++) {
            //columns
            for (int j = 1; j < trees[i].length - 1; j++) {
                Integer tree = trees[i][j];
                int[] visibleTrees = {0, 0, 0, 0};
                //check row left
                for (int k = j - 1; k>=0; k--) {
                    visibleTrees[0]++;
                    if (trees[i][k] >= tree) {
                        break;
                    }
                }
                //check row right
                for (int k = j + 1; k < trees[i].length; k++) {
                    visibleTrees[1]++;
                    if (trees[i][k] >= tree) {
                        break;
                    }
                }
                //check column up
                for (int k = i - 1; k>=0; k--) {
                    visibleTrees[2]++;
                    if (trees[k][j] >= tree) {
                        break;
                    }
                }
                //check column down
                for (int k = i + 1; k < trees.length; k++) {
                    visibleTrees[3]++;
                    if (trees[k][j] >= tree) {
                        break;
                    }
                }
                int scenicScore = Arrays.stream(visibleTrees).reduce(1, (a, b) -> a * b);
                if(scenicScore > highestScenicScore) {
                    highestScenicScore = scenicScore;
                }

            }
        }
        System.out.println(highestScenicScore);
    }
}
