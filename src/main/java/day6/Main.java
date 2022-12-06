package day6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final int PART_1_CHARS = 4;
    private static final int PART_2_CHARS = 14;

    public static void main(String[] args) {
        File file = new File("src/main/java/day6/input.txt");

        String input = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            input = reader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();

        Pattern pattern = Pattern.compile("(?:([A-Za-z])(?!.*\\1)){14}");
        Matcher matcher;

        for(int i = 0; i<input.length(); i++) {
            char c = input.charAt(i);

            if(sb.length() < PART_2_CHARS) {
                sb.append(c);
            }
            if(sb.length() == PART_2_CHARS) {
                matcher = pattern.matcher(sb);
                if(matcher.matches()) {
                    System.out.println("string: " + sb + " result: " + (i + 1));
                    break;
                } else {
                    sb.deleteCharAt(0);
                }
            }
        }
    }
}
