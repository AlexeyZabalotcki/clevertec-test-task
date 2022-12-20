package com.test.task.factories;

import com.test.task.model.Item;

import java.util.Map;

public interface ItemFactory {
    Map<Integer, Item> createItems();

    void writeToFile();
}
