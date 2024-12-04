package de.pascalkuehnold.day3;

import de.pascalkuehnold.interfaces.IAdventOfCodeDay;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Starter implements IAdventOfCodeDay {
    private static final String INPUT_FILE_PATH = "src/main/resources/inputDay3.txt";
    private static final String REGEX = "mul\\(\\d{1,3},\\d{1,3}\\)";
    private static final String removePattern = "don't\\(\\).*?do\\(\\)";

    private int result = 0;
    private int counter = 0;
    private boolean mulInstructionEnabled = true;

    public void start() {
        try {
            // Read the file content and remove newlines
            String content = Files.readString(Paths.get(INPUT_FILE_PATH)).replaceAll("\\r?\\n", "");

            content = content.replaceAll(removePattern, "");

            // Match regex and process
            Matcher matcher = Pattern.compile(REGEX).matcher(content);
            while (matcher.find()) {
                String match = matcher.group();
                System.out.println("Found calculation: " + match);
                result += calculateResult(match);
                counter++;
            }

            System.out.println("Total calculations: " + counter);

            System.out.println("Total calculations result: " + result);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    private int calculateResult(String calculation) {
        // Extract numbers from "mul(a,b)" and calculate a * b
        String[] parts = calculation.substring(4, calculation.length() - 1).split(",");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[1]);
        return a * b;
    }

    public void getResult() {
        System.out.println("Final result: " + result);
    }
}
