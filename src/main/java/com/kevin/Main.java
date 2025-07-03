package com.kevin;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        FileReader reader = new FileReader();
        DataAnalyser analyser = new DataAnalyser();

        try {
            Map<String, Integer> data = reader.readFile("/data.txt");
            Map<String, LinkedHashMap<String, Integer>> dataByDate = reader.getEntriesByDate();

            // Question 1
            int totalCars = analyser.calculateTotalCars(data);
            System.out.println("\nTotal Num. of Cars: " + totalCars);

            // Question 2
            List<String> dailyCount = analyser.calculateDailyCount(dataByDate);
            System.out.println("\nDaily Car Count: ");
            for (String line : dailyCount) {
                System.out.println(line);
            }

            // Question 3
            List<String> topThreePeriods = analyser.findTopThreePeriods(data);
            System.out.println("\nTop 3 Half-hours with the Most Cars: ");
            for (String line : topThreePeriods) {
                System.out.println(line);
            }

            // Question 4



        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


    }
}