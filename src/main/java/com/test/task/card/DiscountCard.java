package com.test.task.card;

import com.test.task.model.Item;
import com.test.task.model.ItemWithDiscount;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DiscountCard implements Card {
    private String title;
    private Integer number;

    public DiscountCard(String title, Integer number) {
        this.title = title;
        this.number = number;
    }

    @Override
    public BigDecimal giveDiscount(Item item) {
        if (item instanceof ItemWithDiscount) {
            BigDecimal price = item.getPrice().subtract(((ItemWithDiscount) item).getDiscount());
            return price;
        } else {
            return item.getPrice();
        }
    }
}
