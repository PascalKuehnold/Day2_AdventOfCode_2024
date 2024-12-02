package de.pascalkuehnold;

import de.pascalkuehnold.util.InputReader;
import de.pascalkuehnold.util.SafetyCalculator;

public class Main {
    public static String inputFilePath = "src/main/resources/input.txt";

    public static void main(String[] args) {
        InputReader inputReader = new InputReader(inputFilePath);

        SafetyCalculator safetyCalculator = new SafetyCalculator(inputReader.getReportsMap());

        safetyCalculator.calculateSafetyDamped();

        int safeReports = safetyCalculator.getSafeReports(false);
        int dampenedReports = safetyCalculator.getSafeReports(true);
        System.out.println("Safe reports: " + safeReports);
        System.out.println("Dampened reports: " + dampenedReports);

        safetyCalculator.printSafeReports();
    }
}