package com.kevin;

import java.util.*;

public class DataAnalyser {

    /** Calculate total number of cars seen by the counter */
    public int calculateTotalCars(Map<String, Integer> data) {
        Collection<Integer> values = data.values();
        return values.stream().mapToInt(Integer::intValue).sum();
    }

    /** Find the top 3 half-hour periods, with the highest no. of cars seen */
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
