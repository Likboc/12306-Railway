package com.example.controller;

import com.example.Results;
import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.result.Result;
import com.example.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 车票查询，使用Redis + MySQL实现
     * @param requestParam
     * @return
     */
    @GetMapping("/api/ticket-service/ticket/query")
    public Result<Object> pageListTicketQuery(TicketPageQueryReqDTO requestParam) {
        return new Result<>().setData(ticketService.pageListTicketQueryV1(requestParam));
    }

    public Result<String> purchaseTicket() {
        return Results.success(ticketService.purchaseTicketsV1());
    }


}
