package com.example.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 封装类，包括 price,quantity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatClassDTO {

    private Integer type;

    private BigDecimal price;

    private Integer quantity;

    private Boolean candidate;
}
