package com.example.service;

import com.example.dto.TicketListDTO;
import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.dto.resp.TicketPageQueryRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TicketService {

    public List<TicketListDTO> pageListTicketQueryV1(TicketPageQueryReqDTO requestParam);
}
