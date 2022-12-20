package com.test.task.card;

import com.test.task.model.Item;

import java.math.BigDecimal;

public interface Card {
    BigDecimal giveDiscount(Item item);
}
