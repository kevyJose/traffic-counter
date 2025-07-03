package com.kevin;

import java.util.*;

public class DataAnalyser {

    /** Qn1: Calculate total number of cars seen by the counter */
    public int calculateTotalCars(Map<String, Integer> data) {
        Collection<Integer> values = data.values();
        return values.stream().mapToInt(Integer::intValue).sum();
    }

    /** Qn2: Per day, calculate the total no. of cars seen */
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

    /** Qn 3: Find the top 3 half-hour periods, with the highest no. of cars seen */
    public List<String> findTopThreePeriods(Map<String, Integer> data) {
        List<String> res = new ArrayList<>();

        // get entries as a list
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(data.entrySet());

        // sort list by value, descending (most no. of cars seen first)
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

        for (int i=0; i < 3; i++) {
            String str = entries.get(i).getKey() + " " + entries.get(i).getValue();
            res.add(str);
        }

        return res;
    }

}
