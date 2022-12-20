package com.test.task.check;

import com.test.task.card.Card;
import com.test.task.factories.CardFactory;
import com.test.task.factories.ItemFactory;
import com.test.task.model.Item;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;

public class FileCheck implements Check {
    private String receiptTitle = "CASH RECEIPT";
    private String marketTitle = "SUPERMARKET 123";
    private String address = "12, MILKWAY Galaxy/Earth";
    private String phone = "Tel. 123-456-7890";
    private String cashier = "CASHIER";
    private Random random = new Random();
    private int cashierNumb = random.nextInt(999);
    private String date = "DATE:";
    private LocalDate currentDate = LocalDate.now();
    private String qty = "QTY";
    private String description = "DESCRIPTION";
    private String price = "PRICE";
    private String total = "TOTAL";
    private String currentTime = "TIME: " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
    private String taxable = "TAXABLE TOT.";
    private String vat = "VAT17%";
    private static BigDecimal fullPrice = BigDecimal.valueOf(0);
    private static BigDecimal discount = BigDecimal.valueOf(0);
    private static BigDecimal finalPrice = BigDecimal.valueOf(0);
    private final Map<Integer, Item> items;
    private final Map<Integer, Card> cards;

    public FileCheck(ItemFactory itemFactory,
                     CardFactory cardFactory) {
        this.items = itemFactory.createItems();
        this.cards = cardFactory.createCards();
        cardFactory.writeToFile();
        itemFactory.writeToFile();
    }

    @Override
    public void printCheck(int index, int quantity, int cardNumber) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("RECEIPT.txt", true)))) {
                String itemTitle = items.get(index).getTitle();
                BigDecimal itemPrice = items.get(index).getPrice();
                BigDecimal totalItemPrice = calculatePrice(index, quantity, cardNumber);
                writer.printf("%s %7s %10s %6s\n", quantity, itemTitle, "$" + itemPrice, "$" + totalItemPrice);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void headOfCheck() {
        createFile();
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("RECEIPT.txt", true)))) {
            writer.printf("%20s\n %21s\n %27s\n %22s\n ", receiptTitle, marketTitle, address, phone);
            writer.printf("%s %9s\n %28s\n", cashier + ":" + cashierNumb, date + " " + currentDate, currentTime);
            writer.println("-----------------------------");
            writer.printf("%s %5s %6s %6s\n", qty, description, price, total);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void checkBottom() {
        BigDecimal taxes = fullPrice.multiply(BigDecimal.valueOf(17)).divide(BigDecimal.valueOf(100), 2);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("RECEIPT.txt", true)))) {
            writer.println("-----------------------------");
            writer.printf("%s %15s \n", taxable, "$" + fullPrice);
            writer.printf("%s %21s \n", vat, "$" + taxes);
            writer.printf("%s %22s \n", total, "$" + (fullPrice.add(taxes)));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private BigDecimal calculatePrice(int index, int quantity, int cardNumber) {
        Card card = cards.get(cardNumber);
        BigDecimal totalPrice;
        if (cardNumber == -1) {
            if (quantity > 5) {
                totalPrice = card.giveDiscount(items.get(index)).multiply(BigDecimal.valueOf(quantity));
            } else {
                totalPrice = items.get(index).getPrice().multiply(BigDecimal.valueOf(quantity));
            }
            fullPrice = totalPrice.add(fullPrice);
            finalPrice = fullPrice;
        } else {
            totalPrice = items.get(index).getPrice().multiply(BigDecimal.valueOf(quantity));
            fullPrice = totalPrice.add(fullPrice);
            discount = fullPrice.add(totalPrice).multiply(BigDecimal.valueOf(10)).divide(BigDecimal.valueOf(100), 2);
            finalPrice = fullPrice.subtract(discount);
        }
        return totalPrice;
    }

    private void createFile() {
        File file = new File("RECEIPT.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
