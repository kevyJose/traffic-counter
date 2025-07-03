package com.kevin;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        FileReader reader = new FileReader();
        DataAnalyser analyser = new DataAnalyser();

        try {
            Map<String, Integer> data = reader.readFile("/data.txt");

            // Question 1
            int totalCars = analyser.calculateTotalCars(data);
            System.out.println("\nTotal num. of cars: " + totalCars);

            // Question 2

            // Question 3
            List<String> topThreePeriods = analyser.findTopThreePeriods(data);
            System.out.println("\nTop 3 half-hours with the most cars: ");
            for (String line : topThreePeriods) {
                System.out.println(line);
            }


        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


    }
}