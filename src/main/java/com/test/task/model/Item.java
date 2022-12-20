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
@EqualsAndHashCode
public abstract class Item {
    protected String title;
    protected BigDecimal price;

    public Item(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public Item() {
    }
}
