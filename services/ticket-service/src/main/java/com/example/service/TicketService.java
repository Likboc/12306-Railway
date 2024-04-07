package com.example.service;

import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.dto.resp.TicketPageQueryRespDTO;

public interface TicketService {

    public TicketPageQueryRespDTO pageListTicketQueryV1(TicketPageQueryReqDTO requestParam);

    public Object purchaseTickets(Object param);
}
