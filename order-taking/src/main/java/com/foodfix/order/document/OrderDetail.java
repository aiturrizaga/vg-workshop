package com.foodfix.order.document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class OrderDetail {
    private Product plate;
    private int quantity;
    private String observation;

    @Getter
    @Setter
    public static class Product {
        private int id;
        private String name;
        private BigDecimal price;
    }
}
