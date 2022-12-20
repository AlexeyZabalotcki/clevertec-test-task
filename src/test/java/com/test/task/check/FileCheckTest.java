package com.test.task.check;

import com.test.task.card.Card;
import com.test.task.card.DiscountCard;
import com.test.task.model.Item;
import com.test.task.model.ItemWithDiscount;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FileCheckTest {

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
        BigDecimal actualFinalPrice;
        BigDecimal totalPrice;
        BigDecimal discount;
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
        BigDecimal actualFinalPrice;
        BigDecimal totalPrice;
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
        BigDecimal actualTaxes;
        BigDecimal expectedTaxes;
        BigDecimal fullPrice;
        fullPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        actualTaxes = fullPrice.multiply(BigDecimal.valueOf(percentages)).divide(BigDecimal.valueOf(100), 2);
        expectedTaxes = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity))
                .multiply(BigDecimal.valueOf(percentages)).divide(BigDecimal.valueOf(100), 2);
        assertEquals(expectedTaxes, actualTaxes);
    }

    @Test
    public void testShouldReturnTrueIfReceiptFileExists() throws IOException {
        assertDoesNotThrow(() -> new File("src/test/resources/RECEIPT.txt"));
    }

    @Test
    public void testShouldReturnTwoSameFiles() {
        int itemIndex = 1;
        int quantity = 2;
        BigDecimal taxes = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal fullPrice = itemsMap.get(itemIndex).getPrice().multiply(BigDecimal.valueOf(quantity));
        String taxable = "TAXEBLE";
        String vat = "VAT17%";
        String total = "TOTAL";
        File file = new File("src/test/resources/RECEIPT1.txt");
        File file1 = new File("src/test/resources/RECEIPT2.txt");
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/test/resources/RECEIPT1.txt", true)))) {
            writer.println("-----------------------------");
            writer.printf("%s %20s \n", taxable, "$" + fullPrice);
            writer.printf("%s %21s \n", vat, "$" + taxes);
            writer.printf("%s %22s \n", total, "$" + (fullPrice.add(taxes)));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileReader reader = new FileReader("src/test/resources/RECEIPT1.txt")) {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("src/test/resources/RECEIPT2.txt", true)))) {
                int c;
                while ((c = reader.read()) != -1) {
                    writer.append((char) c);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        long actual = 0;
        long expected = -1;
        try {
            actual = Files.mismatch(file.toPath(), file1.toPath());
            assertEquals(expected, actual);
        } catch (IOException e) {
            e.getMessage();
        }finally {
            file.deleteOnExit();
            file1.deleteOnExit();
        }

    }
}