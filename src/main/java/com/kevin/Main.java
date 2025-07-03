package com.kevin;

import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        FileReader reader = new FileReader();
        DataAnalyser analyser = new DataAnalyser();

        try {
            Map<String, Integer> data = reader.readFile("/data.txt");
            int totalCars = analyser.calculateTotalCars(data); // Qn. 1
            System.out.println("Total num. of cars: " + totalCars);

        } catch (FileNotFoundException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }


    }
}