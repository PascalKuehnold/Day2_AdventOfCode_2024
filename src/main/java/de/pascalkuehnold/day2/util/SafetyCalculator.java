package de.pascalkuehnold.day2.util;

import java.util.HashMap;
import java.util.Map;

/**
 * The SafetyCalculator class is responsible for calculating the safety of reports.
 */
public class SafetyCalculator {
    private final Map<Integer, String> reportsMap;
    private final Map<Integer, String> safeReportsMap;
    private final Map<Integer, String> unsafeReportsMap;

    /**
     * Constructs a SafetyCalculator and initializes the safety analysis of the reports.
     * @param reportsMap A map containing the reports to be analyzed.
     */
    public SafetyCalculator(Map<Integer, String> reportsMap) {
        this.reportsMap = reportsMap;
        this.safeReportsMap = new HashMap<>();
        this.unsafeReportsMap = new HashMap<>();
        calculateSafetyRaw();
    }

    /**
     * Calculates the safety of reports without dampening, categorizing them as safe or unsafe.
     */
    private void calculateSafetyRaw() {
        reportsMap.forEach((key, value) -> {
            String[] levels = value.split(" ");
            if (isSafe(levels)) {
                safeReportsMap.put(key, value);
            } else {
                unsafeReportsMap.put(key, value);
            }
        });
    }

    /**
     * Calculates the safety of reports with dampening, allowing for a single bad level.
     */
    public void calculateSafetyDamped() {
        unsafeReportsMap.forEach((key, value) -> {
            String[] levels = value.split(" ");
            if (isSafeDamped(levels)) {
                safeReportsMap.putIfAbsent(key, value);
            }
        });
    }

    /**
     * Checks if a report is safe with dampening by tolerating a single bad level.
     * @param levels The levels of the report.
     * @return True if the report is safe with dampening, false otherwise.
     */
    private boolean isSafeDamped(String[] levels) {
        for (int i = 0; i < levels.length; i++) {
            String[] levelsCopy = removeElementAtIndex(levels, i);
            if (isSafe(levelsCopy)) {
                return true;
            }
        }
        return false;
    }

    private String[] removeElementAtIndex(String[] levels, int index) {
        String[] result = new String[levels.length - 1];
        for (int i = 0, j = 0; i < levels.length; i++) {
            if (i != index) {
                result[j++] = levels[i];
            }
        }
        return result;
    }

    /**
     * Checks if a report is safe based on the levels being either all increasing or decreasing
     * and the differences between adjacent levels being within a specified range.
     * @param levels The levels of the report.
     * @return True if the report is safe, false otherwise.
     */
    private boolean isSafe(String[] levels) {
        return (isIncreasing(levels) || isDecreasing(levels)) && isDifferenceInRange(levels);
    }

    /**
     * Checks if the levels are strictly increasing.
     * @param levels The levels of the report.
     * @return True if the levels are increasing, false otherwise.
     */
    private boolean isIncreasing(String[] levels) {
        for (int i = 0; i < levels.length - 1; i++) {
            if (Integer.parseInt(levels[i]) > Integer.parseInt(levels[i + 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the levels are strictly decreasing.
     * @param levels The levels of the report.
     * @return True if the levels are decreasing, false otherwise.
     */
    private boolean isDecreasing(String[] levels) {
        for (int i = 0; i < levels.length - 1; i++) {
            if (Integer.parseInt(levels[i]) < Integer.parseInt(levels[i + 1])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the difference between adjacent levels is within the range of 1 to 3.
     * @param levels The levels of the report.
     * @return True if the difference is within the range, false otherwise.
     */
    private boolean isDifferenceInRange(String[] levels) {
        for (int i = 0; i < levels.length - 1; i++) {
            int difference = Math.abs(Integer.parseInt(levels[i]) - Integer.parseInt(levels[i + 1]));
            if (difference < 1 || difference > 3) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prints the safe reports to the console.
     */
    public void printSafeReports() {
        safeReportsMap.forEach((key, value) -> System.out.println(value));
    }

    /**
     * Gets the number of safe reports.
     * @param dampened Whether to include dampened safety calculation.
     * @return The number of safe reports.
     */
    public int getSafeReports(boolean dampened) {
        if (dampened) {
            calculateSafetyDamped();
        }
        return safeReportsMap.size();
    }
}
