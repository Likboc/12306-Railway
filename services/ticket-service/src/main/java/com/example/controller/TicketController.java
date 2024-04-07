package com.example.controller;


import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.dto.resp.TicketPageQueryRespDTO;
import com.example.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * 车票查询，使用Redis + MySQL实现
     * @param requestParam
     * @return
     */
    @PostMapping("/api/ticket-service/ticket/query")
    public TicketPageQueryRespDTO pageListTicketQuery(@RequestBody TicketPageQueryReqDTO requestParam) {
        return ticketService.pageListTicketQueryV1(requestParam);
    }

    /**
     * 车票购买，调用订单服务，创建新订单
     * @return
     */
    public Object purchaseTicket(Object param) {
        return ticketService.purchaseTickets(param);
    }


}
