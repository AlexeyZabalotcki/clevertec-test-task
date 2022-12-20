package com.test.task.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class ItemWithoutDiscount extends Item {

    public ItemWithoutDiscount(String title, BigDecimal price) {
        super(title, price);
    }

    public ItemWithoutDiscount() {
        super();
    }

}
