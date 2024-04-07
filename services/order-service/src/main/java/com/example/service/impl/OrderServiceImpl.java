package com.example.service.impl;

import com.example.common.enums.OrderStatusEnum;
import com.example.dao.entity.OrderDO;
import com.example.dao.mapper.OrderMapper;
import com.example.dto.req.TicketOrderCreateReqDTO;
import com.example.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    public Object createTicketOrder(TicketOrderCreateReqDTO requestParam) {
        // TODO : 手写一个snowflake
        String orderSn = "hi";
        // 写表 t_order
        OrderDO orderDO = OrderDO.builder().orderSn(orderSn)
                .orderTime(requestParam.getOrderTime())
                .departure(requestParam.getDeparture())
                .departureTime(requestParam.getDepartureTime())
                .ridingDate(requestParam.getRidingDate())
                .arrivalTime(requestParam.getArrivalTime())
                .trainNumber(requestParam.getTrainNumber())
                .arrival(requestParam.getArrival())
                .trainId(requestParam.getTrainId())
                .source(requestParam.getSource())
                .status(OrderStatusEnum.PENDING_PAYMENT.getStatus())
                .username(requestParam.getUsername())
                .userId(String.valueOf(requestParam.getUserId()))
                .build();
        orderMapper.insert(orderDO);
        return orderDO.getOrderSn();
    }
}
