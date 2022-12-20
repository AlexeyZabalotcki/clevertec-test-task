package com.test.task.factories;

import com.test.task.model.Item;
import com.test.task.model.ItemWithDiscount;
import com.test.task.model.ItemWithoutDiscount;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ItemsFactory implements ItemFactory {

    @Override
    public Map<Integer, Item> createItems() {
        Map<Integer, Item> items = new HashMap<>();
        Item item1 = new ItemWithDiscount("item1",new BigDecimal("100"), new BigDecimal("10"));
        Item item8 = new ItemWithDiscount("item8", new BigDecimal("432.80"), new BigDecimal("90"));
        Item item9 = new ItemWithDiscount("item9", new BigDecimal("23.35"), new BigDecimal("4"));
        Item item10 = new ItemWithDiscount("item10", new BigDecimal("552.75"), new BigDecimal("100"));
        Item item11 = new ItemWithDiscount("item10", new BigDecimal("55.5"), new BigDecimal("5"));
        Item item2 = new ItemWithoutDiscount("item2", new BigDecimal("10.5"));
        Item item3 = new ItemWithoutDiscount("item3", new BigDecimal("10.8"));
        Item item4 = new ItemWithoutDiscount("item4", new BigDecimal("10.9"));
        Item item5 = new ItemWithoutDiscount("item5", new BigDecimal("10.1"));
        Item item6 = new ItemWithoutDiscount("item6", new BigDecimal("10"));
        Item item7 = new ItemWithoutDiscount("item7", new BigDecimal("10.95"));


        items.put(1, item1);
        items.put(2, item2);
        items.put(3, item3);
        items.put(4, item4);
        items.put(5, item5);
        items.put(6, item6);
        items.put(7, item7);
        items.put(8, item8);
        items.put(9, item9);
        items.put(10, item10);
        items.put(11, item11);
        return items;
    }

    public void writeToFile() {
        File file = new File("ITEMS.txt");
        Map<Integer, Item> map = createItems();
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<Integer, Item> entry : map.entrySet()) {
                bf.write(entry.getKey() + " : " + entry.getValue().getTitle());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
