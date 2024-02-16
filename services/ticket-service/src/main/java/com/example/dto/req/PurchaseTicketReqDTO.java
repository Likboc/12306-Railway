package com.example.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseTicketReqDTO {

    private String trainId;

    private List<String> chooseSeats;

    private String departure;

    private String arrival;
}
