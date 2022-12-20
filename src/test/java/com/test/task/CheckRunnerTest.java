package com.test.task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CheckRunnerTest {
    private static List<String> enteredStringsInList = new ArrayList<>();
    private static List<Integer> expectedIntegersInList = new ArrayList<>();
    private static List<String> expectedParsedStrings = new ArrayList<>();

    @BeforeAll
    public static void init() {
        String[] enteredStrings = {"1-3", "5-6", "2-3", "7-1", "4-5"};
        enteredStringsInList = Arrays.asList(enteredStrings);
        String one = "1";
        String two = "2";
        String three = "3";
        String four = "4";
        String five = "5";
        String six = "6";
        String seven = "7";
        String eight = "8";
        String nine = "9";
        expectedParsedStrings.add(one);
        expectedParsedStrings.add(three);
        expectedParsedStrings.add(five);
        expectedParsedStrings.add(six);
        expectedParsedStrings.add(two);
        expectedParsedStrings.add(three);
        expectedParsedStrings.add(seven);
        expectedParsedStrings.add(one);
        expectedParsedStrings.add(four);
        expectedParsedStrings.add(five);

        Integer intOne = 1;
        Integer intTwo = 2;
        Integer intThree = 3;
        Integer intFour = 4;
        Integer intFive = 5;
        Integer intSix = 6;
        Integer intSeven = 7;
        Integer intEight = 8;
        Integer intNine = 9;

        expectedIntegersInList.add(intOne);
        expectedIntegersInList.add(intThree);
        expectedIntegersInList.add(intFive);
        expectedIntegersInList.add(intSix);
        expectedIntegersInList.add(intTwo);
        expectedIntegersInList.add(intThree);
        expectedIntegersInList.add(intSeven);
        expectedIntegersInList.add(intOne);
        expectedIntegersInList.add(intFour);
        expectedIntegersInList.add(intFive);
    }

    @Test
    public void testShouldSplitStringsCorrectly() {
        String delimeter = "-";
        List<String> actual = new ArrayList<>();
        for (int i = 0; i < enteredStringsInList.size(); i++) {
            List<String> newString = new ArrayList<>(Arrays.asList(enteredStringsInList.get(i).split(delimeter)));
            actual.addAll(newString);
        }
        assertTrue(expectedParsedStrings.size() == actual.size() &&
                expectedParsedStrings.containsAll(actual) && actual.containsAll(expectedParsedStrings));
    }

    @Test
    public void testShouldCorrectlyParseStringsToIntegers() {
        List<Integer> actualIntegers = expectedParsedStrings.stream().map(Integer::parseInt).collect(Collectors.toList());

        assertTrue(expectedIntegersInList.size() == actualIntegers.size() &&
                expectedIntegersInList.containsAll(actualIntegers) && actualIntegers.containsAll(expectedIntegersInList));
    }

    @Test
    public void testShouldReturnTrueIfDiscountCardExist() {
        String expected = "Call method with discount card";
        String actual = null;
        List<Integer> integers = new ArrayList<>(3);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        while (!integers.isEmpty()) {
            if (integers.size() == 3) {
                actual = "Call method with discount card";
                break;
            } else {
                actual = "Call method without discount card";
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldReturnTrueIfDiscountCardNotExist() {
        String expected = "Call method without discount card";
        String actual = null;
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(3);
        while (!integers.isEmpty()) {
            if (integers.size() == 3) {
                actual = "Call method with discount card";
                break;
            } else {
                actual = "Call method without discount card";
                break;
            }
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testShouldReturnTrueIfListContainsLessThanThreeElements() {
        boolean actual = false;
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(3);
        while (!integers.isEmpty()) {
            if (integers.size() > 3) {
                integers.remove(0);
                integers.remove(0);
            } else {
                actual = integers.size() < 3;
                break;
            }
        }
        assertTrue(actual);
    }

}