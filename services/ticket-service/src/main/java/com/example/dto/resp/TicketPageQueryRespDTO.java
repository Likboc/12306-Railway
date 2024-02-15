package com.example.dto.resp;

import lombok.Data;

@Data
public class TicketPageQueryRespDTO {
    /**
     *  ticket search result
     */
    private String trainID;

    private String from_station;

    private String to_station;

    private long price;

    private long ticket_num;
}
