package de.pascalkuehnold.day2.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InputReader {
    private final Map<Integer, String> reportsMap;

    /**
     * Constructs an InputReader and loads reports from the specified file path.
     * @param inputFilePath The path to the input file.
     */
    public InputReader(String inputFilePath) {
        reportsMap = new HashMap<>();
        loadReports(inputFilePath);
    }

    /**
     * Loads reports from the given file path into the reports map.
     * @param inputFilePath The path to the input file.
     */
    private void loadReports(String inputFilePath) {
        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            int reports = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                reports++;
                reportsMap.put(reports, line);
            }
            System.out.println("InputReader --> Reports: " + reports);
        } catch (Exception e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * Gets a copy of the reports map.
     * @return A copy of the reports map.
     */
    public Map<Integer, String> getReportsMap() {
        return new HashMap<>(reportsMap);
    }
}

