package com.test.task.factories;

import com.test.task.model.Item;
import com.test.task.model.ItemWithDiscount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ItemsFactoryTest {
    private static Map<Integer, Item> itemsMap = new HashMap<>();

    @BeforeAll
    public static void fillMap() {
        Item item1 = new ItemWithDiscount("Item With Discount 1", new BigDecimal(100), new BigDecimal(10));
        Item item2 = new ItemWithDiscount("Item With Discount 2", new BigDecimal(100), new BigDecimal(10));
        Item item3 = new ItemWithDiscount("Item Without Discount", new BigDecimal(100));
        Item item4 = new ItemWithDiscount("Item Without Discount", new BigDecimal(100));

        itemsMap.put(1, item1);
        itemsMap.put(2, item2);
        itemsMap.put(3, item3);
        itemsMap.put(4, item4);
    }

    @Test
    public void testShouldReturnTrueAfterCheckingMapSizes() {
        int mapSize = itemsMap.size();
        assertEquals(4, mapSize);
    }

    @Test
    public void testShouldReturnFalseAfterCheckingMapSizes() {
        int mapSize = itemsMap.size();
        assertNotEquals(6, mapSize);
    }

}