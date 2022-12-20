package com.test.task.check;

import com.test.task.card.Card;
import com.test.task.card.DiscountCard;
import com.test.task.model.Item;
import com.test.task.model.ItemWithDiscount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ConsoleCheckTest {
    private static Map<Integer, Card> cardsMap = new HashMap<>();
    private static Map<Integer, Item> itemsMap = new HashMap<>();


    @BeforeAll
    public static void fillMaps() {
        Card card1 = new DiscountCard("Card1", 1);
        Card card2 = new DiscountCard("Card1", 2);
        Card card3 = new DiscountCard("Card1t", 3);
        Card card4 = new DiscountCard("Card1t", 4);

        cardsMap.put(1, card1);
        cardsMap.put(2, card2);
        cardsMap.put(3, card3);
        cardsMap.put(4, card4);

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
    public void testShouldReturnExpectedTotalPriceForItemsQuantityMoreThanFive() {
        int cardNumber = 1;
        int quantity = 6;
        int itemIndex = 1;
        Card card = cardsMap.get(cardNumber);
        BigDecimal actualTotalPrice;
        if (quantity > 5) {
            actualTotalPrice = card.giveDiscount(itemsMap.get(itemIndex)).multiply(BigDecimal.valueOf(quantity));
        } else {
            actualTotalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        }

        BigDecimal expectedTotalPrice = card.giveDiscount(itemsMap.get(itemIndex)).multiply(BigDecimal.valueOf(quantity));
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void testShouldReturnExpectedTotalPriceForItemsQuantityLessThanFive() {
        int cardNumber = 1;
        int quantity = 3;
        int itemIndex = 1;
        Card card = cardsMap.get(cardNumber);
        BigDecimal actualTotalPrice;
        if (quantity > 5) {
            actualTotalPrice = card.giveDiscount(itemsMap.get(itemIndex)).multiply(BigDecimal.valueOf(quantity));
        } else {
            actualTotalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        }

        BigDecimal expectedTotalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void testShouldReturnWrongTotalPriceForItemsQuantityMoreThanFive() {
        int cardNumber = 1;
        int quantity = 3;
        int itemIndex = 1;
        Card card = cardsMap.get(cardNumber);
        BigDecimal actualTotalPrice;
        if (quantity > 5) {
            actualTotalPrice = card.giveDiscount(itemsMap.get(itemIndex)).multiply(BigDecimal.valueOf(quantity));
        } else {
            actualTotalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        }
        BigDecimal expectedTotalPrice = card.giveDiscount(itemsMap.get(itemIndex)).multiply(BigDecimal.valueOf(quantity));

        assertNotEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test
    public void testShouldReturnFinalPriceWithoutDiscount() {
        int cardNumber = 1;
        int quantity = 3;
        int itemIndex = 1;
        BigDecimal actualFinalPrice = BigDecimal.valueOf(0);
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        BigDecimal discount = BigDecimal.valueOf(0);
        BigDecimal fullPriceWithoutDisc = BigDecimal.valueOf(0);
        BigDecimal fullPriceWithDisc = BigDecimal.valueOf(0);
        if (cardNumber != -1) {
            totalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
            fullPriceWithoutDisc = totalPrice.add(fullPriceWithoutDisc);
            actualFinalPrice = fullPriceWithoutDisc;
        } else {
            totalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
            fullPriceWithDisc = totalPrice.add(fullPriceWithDisc);
            discount = fullPriceWithDisc.add(totalPrice).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100));
            actualFinalPrice = fullPriceWithDisc.subtract(discount);
        }
        BigDecimal expectedFinalPrice = fullPriceWithoutDisc;

        assertEquals(expectedFinalPrice, actualFinalPrice);
    }

    @Test
    public void testShouldReturnFinalPriceWithDiscount() {
        int cardNumber = -1;
        int quantity = 3;
        int itemIndex = 1;
        BigDecimal actualFinalPrice = BigDecimal.valueOf(0);
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        BigDecimal discount = BigDecimal.valueOf(0);
        BigDecimal fullPriceWithoutDisc = BigDecimal.valueOf(0);
        BigDecimal fullPriceWithDisc = BigDecimal.valueOf(0);
        if (cardNumber != -1) {
            totalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
            fullPriceWithoutDisc = totalPrice.add(fullPriceWithoutDisc);
            actualFinalPrice = fullPriceWithoutDisc;
        } else {
            totalPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
            fullPriceWithDisc = totalPrice.add(fullPriceWithDisc);
            discount = fullPriceWithDisc.add(totalPrice).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100));
            actualFinalPrice = fullPriceWithDisc.subtract(discount);
        }
        BigDecimal expectedFinalPrice = fullPriceWithDisc.subtract(discount);

        assertEquals(expectedFinalPrice, actualFinalPrice);
    }

    @Test
    public void testShouldReturnCorrectTaxesSurcharge() {
        int quantity = 5;
        int itemIndex = 2;
        int percentages = 17;
        BigDecimal actualTaxes = BigDecimal.valueOf(0);
        BigDecimal expectedTaxes = BigDecimal.valueOf(0);
        BigDecimal fullPrice = BigDecimal.valueOf(0);
        fullPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        actualTaxes = fullPrice.multiply(BigDecimal.valueOf(percentages)).divide(BigDecimal.valueOf(100), 2);
        expectedTaxes = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity))
                .multiply(BigDecimal.valueOf(percentages)).divide(BigDecimal.valueOf(100), 2);
        assertEquals(expectedTaxes, actualTaxes);
    }

}