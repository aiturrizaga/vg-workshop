package com.foodfix.order.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "order")
public class Order {

    @Id
    private String id;
    @Positive(message = "Table number must be positive")
    private int tableNumber;
    private LocalDateTime saleDate = LocalDateTime.now();
    private List<OrderDetail> details;

}
