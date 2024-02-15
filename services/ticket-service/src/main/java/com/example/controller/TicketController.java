package com.example.controller;

import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.result.Result;
import com.example.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/api/ticket-service/ticket/query")
    public Result<Object> pageListTicketQuery(TicketPageQueryReqDTO requestParam) {
        return new Result<>().setData(ticketService.pageListTicketQueryV1(requestParam));
    }

}
