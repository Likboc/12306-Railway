package com.example.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.dto.SeatClassDTO;
import com.example.dto.TicketListDTO;
import com.example.dto.TrainStationPriceDO;
import com.example.dto.req.PurchaseTicketReqDTO;
import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.dto.resp.TicketPageQueryRespDTO;
import com.example.service.TicketService;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.example.common.constant.RedisKeyConstant.*;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public List<TicketListDTO> pageListTicketQueryV1(TicketPageQueryReqDTO requestParam) {
        // 检查参数,此处代码省略

        // 获取车站code对应具体城市名称。 BJP -> HZH ：北京，杭州
        List<Object> stationDetails = stringRedisTemplate.opsForHash().multiGet(REGION_TRAIN_STATION_MAPPING, Lists.newArrayList(requestParam.getFromStation(),requestParam.getToStation()));
        long num = stationDetails.stream().filter(Objects::isNull).count();
        if(num > 0) {
            // 获取分布式锁，写入缓存，代码省略
            System.out.println("error");
        }
        // 拼接redis key
        String buildRegionTrainStationHashKey = String.format(REGION_TRAIN_STATION,stationDetails.get(0),stationDetails.get(1));

        // 获取两城市所有车次信息
        Map<Object,Object> regionTrainStationAllMap = stringRedisTemplate.opsForHash().entries(buildRegionTrainStationHashKey);

        // 所有车次结果 （不含价格）
        List<TicketListDTO> seatResults = regionTrainStationAllMap.values().stream().map(each -> JSON.parseObject(each.toString(),TicketListDTO.class)).toList();

        // 按时间排序，代码省略

        // 遍历获取车票价格和数量
        for(TicketListDTO each : seatResults) {
            // 获取价格
            String ticketPrice = stringRedisTemplate.opsForValue().get(String.format(TRAIN_STATION_PRICE,each.getTrainId(),each.getDeparture(),each.getArrival()));
            // 设置信息
            List<TrainStationPriceDO> priceList = JSON.parseArray(ticketPrice, TrainStationPriceDO.class);
            // 遍历列表获取车票余量
            List<SeatClassDTO> seatList = new ArrayList<>();
            for(TrainStationPriceDO seat : priceList) {
                // 获取余量
                Joiner join = Joiner.on("_");
                String keySuffix = join.join(Lists.newArrayList( each.getTrainId() , each.getDeparture() , each.getArrival()));
                Object ticketNum = stringRedisTemplate.opsForHash().get(TRAIN_STATION_REMAINING_TICKET + keySuffix,seat.getSeatType().toString());
                // 设置信息
                seatList.add(new SeatClassDTO(seat.getSeatType(),new BigDecimal(seat.getPrice()),Integer.parseInt(ticketNum.toString()),false));
            }
            each.setSeatClassList(seatList);
        }

        // return DTO
        return seatResults;
    }

    public Object purchaseTicket(PurchaseTicketReqDTO requestParam) {
        // 参数应该包括 trainID,from_station,arrival_station,seatType,4个信息
        // 买票操作应该开启事务，涉及到金钱必须开启事务
        // 涉及到的有，1. 微信支付， 2 redis 余票 - 1
        hashKey = ;
        int ticketNum = stringRedisTemplate.opsForHash().get();
        if(ticketNum<0) {
            System.out.println("ticket run out");
        }else {
            // 更新余票数量
            stringRedisTemplate.opsForHash().set()
        }
    }
}
