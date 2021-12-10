package com.foodfix.management.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
public class OrderDTO {
    private String id;
    @NotNull
    @Positive
    private Integer tableNumber;
    private List<OrderDetailDTO> details;
}