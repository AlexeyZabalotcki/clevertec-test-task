package com.test.task.factories;

import com.test.task.card.Card;
import com.test.task.card.DiscountCard;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CardsFactoryTest {

    private static Map<Integer, Card> cardsMap = new HashMap<>();

    @BeforeAll
    public static void fillMap() {
        Card card1 = new DiscountCard("Card1", 1);
        Card card2 = new DiscountCard("Card1", 2);
        Card card3 = new DiscountCard("Card1t", 3);
        Card card4 = new DiscountCard("Card1t", 4);

        cardsMap.put(1, card1);
        cardsMap.put(2, card2);
        cardsMap.put(3, card3);
        cardsMap.put(4, card4);
    }

    @Test
    public void testShouldReturnTrueAfterCheckingMapSizes() {
        int mapSize = cardsMap.size();
        assertEquals(4, mapSize);
    }

    @Test
    public void testShouldReturnFalseAfterCheckingMapSizes() {
        int mapSize = cardsMap.size();
        assertNotEquals(6, mapSize);
    }

}