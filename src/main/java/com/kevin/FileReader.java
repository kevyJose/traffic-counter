package com.kevin;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class FileReader {

    private final Map<String, LinkedHashMap<String, Integer>> entriesByDate = new HashMap<>(); // date : half-hour entries


    public Map<String, Integer> readFile(String filepath) throws FileNotFoundException {
        InputStream inputStream = FileReader.class.getResourceAsStream(filepath);

        if (inputStream == null) {
            System.out.println("File not found in resources!");
            return null;
        }

        Map<String, Integer> map = new HashMap<>(); // timestamp : numCars
        try (Scanner sc = new Scanner(inputStream)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                // all entries
                String timestamp = line.substring(0, 19); //test: check the stamp
                int numCars = Integer.parseInt(line.substring(20));
                map.put(timestamp, numCars);

                // entries by date
                String date = line.substring(0, 10);
                if (! entriesByDate.containsKey(date)) {
                    entriesByDate.put(date, new LinkedHashMap<String, Integer>());
                    entriesByDate.get(date).put(timestamp, numCars);
                } else {
                    entriesByDate.get(date).put(timestamp, numCars);
                }
            }
        }
        return map;
    }

    /** Returns the data entries, grouped by date (a map of maps) */
    public Map<String, LinkedHashMap<String, Integer>> getEntriesByDate() {
        return entriesByDate;
    }


}
