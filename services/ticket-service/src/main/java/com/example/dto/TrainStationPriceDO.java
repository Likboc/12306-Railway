package com.example.dto;

import lombok.Data;

@Data
public class TrainStationPriceDO {
    private Long id;

    private Long trainId;

    private Integer seatType;

    private String departure;

    private String arrival;

    private Integer price;
}
