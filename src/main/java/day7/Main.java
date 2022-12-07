package day7;

import java.io.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        java.io.File file = new File("src/main/java/day7/input.txt");

        List<String> input = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null) {
                input.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Directory> allDirectories = new ArrayList<>();

        Directory currentDirectory = new Directory();
        currentDirectory.setName("/");

        allDirectories.add(currentDirectory);

        for(String line : input) {
            if(line.equals("$ cd /")) {
                continue;
            }
            if(line.startsWith("$ cd")) {
                if(line.equals("$ cd ..")) {
                    currentDirectory = currentDirectory.getParentDirectory();
                } else {
                    currentDirectory = currentDirectory.getNestedDirectories().get(line.substring(5));
                }
            }

            if(Character.isDigit(line.charAt(0))) {
                String[] fileProps = line.split(" ");
                currentDirectory.getFiles().add(new AdventFile(Integer.parseInt(fileProps[0]), fileProps[1], currentDirectory));
            }

            if(line.startsWith("dir")) {
                Directory newDir = new Directory();
                newDir.setName(line.substring(4));
                newDir.setParentDirectory(currentDirectory);
                allDirectories.add(newDir);
                currentDirectory.getNestedDirectories().put(newDir.getName(), newDir);
            }
        }

        //part1
        System.out.println(allDirectories.stream()
                .map(Directory::size)
                .filter(size -> size <= 100000)
                .reduce(0, Integer::sum));

        //part2
        final int totalDiskSpace = 70000000;
        final int requiredSpaceForUpdate = 30000000;
        final int diskSpaceUsed = allDirectories.get(0).size();
        final int freeSpace = totalDiskSpace - diskSpaceUsed;
        final int spaceToFree = requiredSpaceForUpdate - freeSpace;

        System.out.println(allDirectories.stream()
                .map(Directory::size)
                .filter(size -> size >= spaceToFree)
                .min(Integer::compareTo)
                .get());
    }
}
