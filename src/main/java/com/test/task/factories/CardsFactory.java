package com.test.task.factories;

import com.test.task.card.Card;
import com.test.task.card.DiscountCard;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CardsFactory implements CardFactory {

    @Override
    public Map<Integer, Card> createCards() {
        Map<Integer, Card> cards = new HashMap<>();
        Card discountCard1 = new DiscountCard("Card1", 1);
        Card discountCard2 = new DiscountCard("Card2", 2);
        Card discountCard3 = new DiscountCard("Card3", 3);
        Card discountCard4 = new DiscountCard("Card4", 4);
        Card discountCard5 = new DiscountCard("Card5", 5);
        Card discountCard6 = new DiscountCard("Card6", 6);
        Card discountCard7 = new DiscountCard("Card7", 7);
        Card discountCard8 = new DiscountCard("Card8", 8);
        Card discountCard9 = new DiscountCard("Card9", 9);
        Card discountCard10 = new DiscountCard("Card10", 10);
        Card discountCard11 = new DiscountCard("Card11", 11);
        Card discountCard12 = new DiscountCard("Without card", -1);
        cards.put(1, discountCard1);
        cards.put(2, discountCard2);
        cards.put(3, discountCard3);
        cards.put(4, discountCard4);
        cards.put(5, discountCard5);
        cards.put(6, discountCard6);
        cards.put(7, discountCard7);
        cards.put(8, discountCard8);
        cards.put(9, discountCard9);
        cards.put(10, discountCard10);
        cards.put(11, discountCard11);
        cards.put(-1, discountCard12);
        return cards;
    }

    public void writeToFile() {
        File file = new File("CARDS.txt");
        Map<Integer, Card> map = createCards();
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<Integer, Card> entry : map.entrySet()) {
                bf.write(entry.getKey() + " : " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
