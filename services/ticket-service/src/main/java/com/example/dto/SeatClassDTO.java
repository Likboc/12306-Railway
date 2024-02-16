package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SeatClassDTO {

    private Integer type;

    private BigDecimal price;

    private Integer quantity;

    private Boolean candidate;
}
