package com.test.task.factories;

import com.test.task.card.Card;

import java.util.Map;

public interface CardFactory {
    Map<Integer, Card> createCards();

    void writeToFile();
}
