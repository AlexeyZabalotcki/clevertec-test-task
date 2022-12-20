package com.test.task.card;

import com.test.task.model.Item;
import com.test.task.model.ItemWithDiscount;
import com.test.task.model.ItemWithoutDiscount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DiscountCardTest {

    @Test
    public void testShouldReturnPriceForItemWithDiscount() {
        Item item = new ItemWithDiscount("Test item with discount", new BigDecimal(100), new BigDecimal(10));
        BigDecimal actualPrice;
        if (item instanceof ItemWithDiscount) {
            actualPrice = item.getPrice().subtract(((ItemWithDiscount) item).getDiscount());
        } else {
            actualPrice = item.getPrice();
        }
        BigDecimal expectedPrice = item.getPrice().subtract(((ItemWithDiscount) item).getDiscount());
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testShouldReturnPriceForItemWithoutDiscount() {
        Item item = new ItemWithoutDiscount("Test item with discount", new BigDecimal(100));
        BigDecimal actualPrice;
        if (item instanceof ItemWithDiscount) {
            actualPrice = item.getPrice().subtract(((ItemWithDiscount) item).getDiscount());
        } else {
            actualPrice = item.getPrice();
        }
        BigDecimal expectedPrice = item.getPrice();
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testShouldReturnCastException() {
        Item item = new ItemWithoutDiscount("Test item with discount", new BigDecimal(100));

        ClassCastException thrown = Assertions.assertThrows(ClassCastException.class, () -> {
            BigDecimal actualPrice = item.getPrice().subtract(((ItemWithDiscount) item).getDiscount());
        });
        String expectedException =
                "class com.test.task.model.ItemWithoutDiscount cannot be cast to class com.test.task.model." +
                        "ItemWithDiscount (com.test.task.model.ItemWithoutDiscount and com.test.task.model." +
                        "ItemWithDiscount are in unnamed module of loader 'app')";
        assertEquals(expectedException, thrown.getMessage());
    }

}
