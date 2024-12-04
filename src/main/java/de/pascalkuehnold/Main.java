package de.pascalkuehnold;

import de.pascalkuehnold.day2.Day2Starter;
import de.pascalkuehnold.day3.Day3Starter;

public class Main {

    public static void main(String[] args) {
        // StartAdventOfCodeDay2();

        // StartAdventOfCodeDay3();
    }

    private static void StartAdventOfCodeDay2() {
        Day2Starter day2Starter = new Day2Starter();
        day2Starter.start();
    }

    private static void StartAdventOfCodeDay3() {
        Day3Starter day3Starter = new Day3Starter();
        day3Starter.start();
        day3Starter.getResult();
    }
}