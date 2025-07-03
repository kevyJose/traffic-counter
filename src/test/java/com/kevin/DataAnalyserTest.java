package com.kevin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class DataAnalyserTest {

    private final DataAnalyser analyser = new DataAnalyser();

    //  QUESTION 1
    /**
     * Normal-case */
    @Test
    void testCalculateTotalCars_1() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("2023-07-01T08:00:00", 10);
        data.put("2023-07-01T08:30:00", 20);
        data.put("2023-07-01T09:00:00", 30);

        int total = analyser.calculateTotalCars(data);
        assertEquals(60, total);
    }


    /**
     * - Added in "seconds" values
     * - Non-contiguous timestamps  */
    @Test
    void testCalculateTotalCars_2() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("2023-07-01T08:00:00", 10);
        data.put("2023-07-01T08:30:00", 15);
        data.put("2023-07-02T09:00:00", 20);
        data.put("2023-07-02T09:30:11", 5);
        data.put("2023-07-03T11:00:55", 25);
        data.put("2023-07-03T13:30:01", 30);

        int total = analyser.calculateTotalCars(data);
        assertEquals(105, total);
    }


    /**
     * Edge-case: empty-map  */
    @Test
    void testCalculateTotalCars_3() {
        Map<String, Integer> data = new LinkedHashMap<>();

        int total = analyser.calculateTotalCars(data);
        assertEquals(0, total);
    }


    /**
     * Edge-case: data is null
     * Ensures that exception is thrown */
    @Test
    void testCalculateTotalCars_4() {
        Map<String, Integer> data = null;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            analyser.calculateTotalCars(data);
        });

        assertEquals("Input data must not be null.", exception.getMessage());
    }


    // QUESTION 2
    /**
     * Normal case: with multiple days and values */
    @Test
    void testCalculateDailyCount_1() {
        Map<String, LinkedHashMap<String, Integer>> data = new LinkedHashMap<>();

        LinkedHashMap<String, Integer> day1 = new LinkedHashMap<>();
        day1.put("2023-07-01T08:00:00", 10);
        day1.put("2023-07-01T08:30:00", 15);
        LinkedHashMap<String, Integer> day2 = new LinkedHashMap<>();
        day2.put("2023-07-02T09:00:00", 20);
        day2.put("2023-07-02T09:30:00", 5);

        data.put("2023-07-01", day1);
        data.put("2023-07-02", day2);

        List<String> result = analyser.calculateDailyCount(data);
        List<String> expected = Arrays.asList("2023-07-01 25", "2023-07-02 25");

        assertEquals(expected, result);
    }


    /**
     * Single day with multiple entries */
    @Test
    void testCalculateDailyCount_2() {
        Map<String, LinkedHashMap<String, Integer>> data = new LinkedHashMap<>();

        LinkedHashMap<String, Integer> day = new LinkedHashMap<>();
        day.put("2023-07-03T10:00:00", 12);
        day.put("2023-07-03T10:30:00", 18);
        day.put("2023-07-03T11:00:00", 20);

        data.put("2023-07-03", day);

        List<String> result = analyser.calculateDailyCount(data);
        List<String> expected = Collections.singletonList("2023-07-03 50");

        assertEquals(expected, result);
    }


    /**
     * Edge-case: empty map */
    @Test
    void testCalculateDailyCount_3() {
        Map<String, LinkedHashMap<String, Integer>> data = new LinkedHashMap<>();

        List<String> result = analyser.calculateDailyCount(data);

        assertTrue(result.isEmpty());
    }


    /**
     * Edge-case: a day with no values (empty day) */
    @Test
    void testCalculateDailyCount_4() {
        Map<String, LinkedHashMap<String, Integer>> data = new LinkedHashMap<>();

        LinkedHashMap<String, Integer> emptyDay = new LinkedHashMap<>();
        data.put("2023-07-04", emptyDay);

        List<String> result = analyser.calculateDailyCount(data);
        List<String> expected = Collections.singletonList("2023-07-04 0");

        assertEquals(expected, result);
    }


    //  QUESTION 3
    /**
     * Normal-case with more than 3 entries */
    @Test
    void testFindTopThreePeriods_1() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("2023-07-01T08:00:00", 10);
        data.put("2023-07-01T08:30:00", 40);
        data.put("2023-07-01T09:00:00", 259);
        data.put("2023-07-01T09:30:00", 300);
        data.put("2023-07-01T10:00:00", 15);
        data.put("2023-07-02T08:00:00", 10);
        data.put("2023-07-02T08:30:00", 101);
        data.put("2023-07-02T14:00:00", 25);
        data.put("2023-07-03T09:30:00", 399);
        data.put("2023-07-03T13:00:00", 110);

        List<String> result = analyser.findTopThreePeriods(data);
        List<String> expected = Arrays.asList(
                "2023-07-03T09:30:00 399",
                "2023-07-01T09:30:00 300",
                "2023-07-01T09:00:00 259"
        );

        assertEquals(expected, result);
    }


    /**
     * Edge-case: fewer than 3 entries
     * Should trigger IndexOutOfBounds exception */
    @Test
    void testFindTopThreePeriods_2() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("2023-07-03T08:00:00", 18);
        data.put("2023-07-03T08:30:00", 22);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            analyser.findTopThreePeriods(data);
        });

        assertTrue(exception.getMessage().contains("Index"));
    }


    // QUESTION 4
    /**
     * Normal-case: There are multiple 90-min windows
     * The optimal window is in the middle of entries
     * (diff. to sample data, where optimal is the first 3 entries) */
    @Test
    void testFindQuietestNinetyMinPeriod_1() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("2023-07-01T06:00:00", 20);
        data.put("2023-07-01T06:30:00", 25);
        data.put("2023-07-01T07:00:00", 15);
        data.put("2023-07-01T07:30:00", 5);
        data.put("2023-07-01T08:00:00", 5);
        data.put("2023-07-01T08:30:00", 5);
        data.put("2023-07-01T09:00:00", 50);

        List<String> result = analyser.findQuietestNinetyMinPeriod(data);

        List<String> expected = Arrays.asList(
                "2023-07-01T07:30:00 5",
                "2023-07-01T08:00:00 5",
                "2023-07-01T08:30:00 5"
        );

        assertEquals(expected, result);
    }


    /** Edge-case: less than 3 entries */
    @Test
    void testFindQuietestNinetyMinPeriod_2() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("2023-07-01T08:00:00", 5);
        data.put("2023-07-01T08:30:00", 7);

        List<String> result = analyser.findQuietestNinetyMinPeriod(data);

        // Should return an empty list
        assertTrue(result.isEmpty());
    }


}
