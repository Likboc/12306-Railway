package com.example.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TicketPageQueryReqDTO {

    private String fromStation;

    private String toStation;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date departureDate;
}
