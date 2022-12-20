package com.test.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ItemWithDiscount extends ItemWithoutDiscount {
    private BigDecimal discount;

    public ItemWithDiscount(String title, BigDecimal price, BigDecimal discount) {
        super(title, price);
        this.discount = discount;
    }

    public ItemWithDiscount(String title, BigDecimal price) {
        super(title, price);
    }

}
