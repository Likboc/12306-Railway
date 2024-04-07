package com.example.service.cache;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.entity.SeatDO;
import com.example.dao.entity.TrainDO;
import com.example.dao.mapper.SeatMapper;
import com.example.dao.mapper.TrainMapper;
import com.example.dto.domain.RouteDTO;
import com.example.service.TrainStationService;
import com.google.common.base.Joiner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.common.constant.RedisKeyConstant.TRAIN_STATION_REMAINING_TICKET;

@Component
@RequiredArgsConstructor
public class SeatMarginCacheLoader {

    private final TrainMapper trainMapper;
    private final SeatMapper seatMapper;
    private final TrainStationService trainStationService;

    /**
     * 获取所有路线的座位信息
     * @param trainId
     * @param seatType
     * @param depature
     * @param arrival
     * @return
     */
    public Map<String,String> load(String trainId,String seatType,String depature,String arrival) {
        Map<String,Map<String,String>> result = new LinkedHashMap<>();
        String keySuffix = Joiner.on("_").join(trainId,depature,arrival);
        // 查表 t_train
        TrainDO trainDO = trainMapper.selectById(trainId);
        // 所有的路线图
        List<RouteDTO> routeDTOList = trainStationService.listTrainStationRoute(trainId,depature,arrival);
        switch(trainDO.getTrainType()) {
            case 0 -> {
                for (RouteDTO each : routeDTOList) {
                    Map<String, String> trainStationRemainingTicket = new LinkedHashMap<>();
                    // 查询 0~2所有座位余票数量
                    trainStationRemainingTicket.put("0", selectSeatMargin(trainId, 0, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("1", selectSeatMargin(trainId, 1, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("2", selectSeatMargin(trainId, 2, each.getStartStation(), each.getEndStation()));
                    String actualKeySuffix = Joiner.on("_").join(trainId, each.getStartStation(), each.getEndStation());
                    result.put(TRAIN_STATION_REMAINING_TICKET + actualKeySuffix, trainStationRemainingTicket);
                }
            }
            case 1 -> {
                for (RouteDTO each : routeDTOList) {
                    Map<String, String> trainStationRemainingTicket = new LinkedHashMap<>();
                    trainStationRemainingTicket.put("3", selectSeatMargin(trainId, 3, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("4", selectSeatMargin(trainId, 4, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("5", selectSeatMargin(trainId, 5, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("13", selectSeatMargin(trainId, 13, each.getStartStation(), each.getEndStation()));
                    String actualKeySuffix = Joiner.on("_").join(trainId, each.getStartStation(), each.getEndStation());
                    result.put(TRAIN_STATION_REMAINING_TICKET + actualKeySuffix, trainStationRemainingTicket);
                }
            }
            case 2 -> {
                for (RouteDTO each : routeDTOList) {
                    Map<String, String> trainStationRemainingTicket = new LinkedHashMap<>();
                    trainStationRemainingTicket.put("6", selectSeatMargin(trainId, 6, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("7", selectSeatMargin(trainId, 7, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("8", selectSeatMargin(trainId, 8, each.getStartStation(), each.getEndStation()));
                    trainStationRemainingTicket.put("13", selectSeatMargin(trainId, 13, each.getStartStation(), each.getEndStation()));
                    String actualKeySuffix = Joiner.on("_").join(trainId, each.getStartStation(), each.getEndStation());
                    result.put(TRAIN_STATION_REMAINING_TICKET + actualKeySuffix, trainStationRemainingTicket);
                }
            }
        }
        return result.get(TRAIN_STATION_REMAINING_TICKET + keySuffix);
    }

    private String selectSeatMargin(String trainId,Integer type,String departure,String arrival) {
        // 查表 t_seat
        LambdaQueryWrapper<SeatDO> seatDOLambdaQueryWrapper = Wrappers.lambdaQuery(SeatDO.class)
                .eq(SeatDO::getTrainId,trainId)
                .eq(SeatDO::getStartStation,departure)
                .eq(SeatDO::getEndStation,arrival)
                .eq(SeatDO::getSeatType,type);
        return String.valueOf(seatMapper.selectCount(seatDOLambdaQueryWrapper));
    }

}
