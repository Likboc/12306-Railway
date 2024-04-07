package com.example.controller;

import com.example.Results;
import com.example.dto.req.TicketOrderCreateReqDTO;
import com.example.result.Result;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TicketOrderController {

    private final OrderService orderService;

    /**
     * 车票订单创建
     */
    @PostMapping("/api/order-service/order/ticket/create")
    public Result<String> createTicketOrder(@RequestBody TicketOrderCreateReqDTO requestParam) {
        return Results.success((String) orderService.createTicketOrder(requestParam));
    }
}
