package com.foodfix.management.dto;

import com.foodfix.management.model.Plate;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class OrderDetailDTO {

    @NotNull
    private Plate plate;

    @NotNull
    private int quantity;

    private String observation;
}
