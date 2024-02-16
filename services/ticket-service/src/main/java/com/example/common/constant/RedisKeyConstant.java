package com.example.common.constant;

public final class RedisKeyConstant {

    public static final String TRAIN_INFO = "index12306-ticket-service:train_info:";

    public static final String REGION_TRAIN_STATION_MAPPING = "index12306-ticket-service:region_train_station_mapping";

    public static final String REGION_TRAIN_STATION = "index12306-ticket-service:region_train_station:%s_%s";

    public static final String TRAIN_STATION_PRICE = "index12306-ticket-service:train_station_price:%s_%s_%s";

    /**
     *  车票余量查询Key,需要手动拼接trainID,from & to station
     */
    public static final String TRAIN_STATION_REMAINING_TICKET = "index12306-ticket-service:train_station_remaining_ticket:";

}