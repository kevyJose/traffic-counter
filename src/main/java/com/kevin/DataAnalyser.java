package com.kevin;

import java.util.*;

public class DataAnalyser {

    public int calculateTotalCars(Map<String, Integer> data) {
        Collection<Integer> values = data.values();
        return values.stream().mapToInt(Integer::intValue).sum();
    }

}
