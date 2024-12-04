package de.pascalkuehnold.day2;

import de.pascalkuehnold.day2.util.InputReader;
import de.pascalkuehnold.day2.util.SafetyCalculator;
import de.pascalkuehnold.interfaces.IAdventOfCodeDay;

public class Day2Starter implements IAdventOfCodeDay {
    public static String inputFilePath = "src/main/resources/inputDay2.txt";


    public void start() {
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
