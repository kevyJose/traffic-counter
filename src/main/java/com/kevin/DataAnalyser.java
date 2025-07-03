package com.kevin;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class DataAnalyser {

    /** Qn1:
     * Calculate total number of cars seen by the counter */
    public int calculateTotalCars(Map<String, Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data must not be null.");
        }

        Collection<Integer> values = data.values();
        return values.stream().mapToInt(Integer::intValue).sum();
    }

    /** Qn2:
     * Per day, calculate the total no. of cars seen */
    public List<String> calculateDailyCount(Map<String, LinkedHashMap<String, Integer>> data) {
        List<String> res = new ArrayList<>();

        for (String date : data.keySet()) {
            Collection<Integer> values = data.get(date).values();
            int sum = values.stream().mapToInt(Integer::intValue).sum();
            String str = date + " " + sum;
            res.add(str);
        }

        return res;
    }

    /** Qn 3:
     * Find the top 3 half-hour periods, with the highest no. of cars seen */
    public List<String> findTopThreePeriods(Map<String, Integer> data) {
        List<String> res = new ArrayList<>();

        // get entries as a list
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(data.entrySet());

        // sort list by values, descending (most no. of cars seen first)
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        // extract the top three entries from sorted list
        for (int i=0; i < 3; i++) {
            String str = entries.get(i).getKey() + " " + entries.get(i).getValue();
            res.add(str);
        }

        return res;
    }

    /** Qn 4:
     * Find the quietest 90-min period (3 contiguous half-hours).
     * Uses the fixed-size sliding-window approach */
    public List<String> findQuietestNinetyMinPeriod(Map<String, Integer> data) {
        List<String> res = new ArrayList<>();

        int minNumCars = Integer.MAX_VALUE;
        int bestStart = 0;
        int bestEnd = 0;
        List<Map.Entry<String, Integer>> dataEntries = new ArrayList<>(data.entrySet());

        // edge-case: cannot proceed if less than 3 entries
        if (dataEntries.size() < 3) {
            return res;
        }

        // sliding-window approach to find the optimal 90-min window
        int left = 0;
        for (int right = 2; right < dataEntries.size(); right++) {
            LocalDateTime start = LocalDateTime.parse(dataEntries.get(left).getKey());
            LocalDateTime end = LocalDateTime.parse(dataEntries.get(right).getKey());
            long timeGap = Duration.between(start, end).getSeconds(); // diff between the two timestamps

            if (timeGap == 3600) { // this time-gap equates to contiguous 90-min period
                int numCars = 0;
                for (int i = left; i <= right; i++) {
                    numCars += dataEntries.get(i).getValue();
                }
                //update optimal window
                if (numCars < minNumCars) {
                    minNumCars = numCars;
                    bestStart = left;
                    bestEnd = right;
                }
            }
            left++;
        }

        // build the resulting list of strings, based on optimal window
        for (int i = bestStart; i <= bestEnd; i++) {
            String str = dataEntries.get(i).getKey() + " " + dataEntries.get(i).getValue();
            res.add(str);
        }

        return res;
    }

}
