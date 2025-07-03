package com.kevin;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class FileReader {

    public Map<String, Integer> readFile(String filepath) throws FileNotFoundException {
        InputStream inputStream = FileReader.class.getResourceAsStream(filepath);

        if (inputStream == null) {
            System.out.println("File not found in resources!");
            return null;
        }

        Set<String> dates = new HashSet<>();
        Map<String, Integer> map = new HashMap<>(); // timestamp : numCars
        try (Scanner sc = new Scanner(inputStream)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String date = line.substring(0, 10);
                String timestamp = line.substring(0, 19); //test: check the stamp
                int numCars = Integer.parseInt(line.substring(20));
                map.put(timestamp, numCars);
                dates.add(date);
            }
        }

        return map;
    }




}
