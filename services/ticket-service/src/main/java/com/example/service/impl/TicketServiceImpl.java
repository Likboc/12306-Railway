package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.entity.*;
import com.example.dao.mapper.*;
import com.example.dto.domain.SeatClassDTO;
import com.example.dto.domain.TicketListDTO;
import com.example.dto.req.TicketPageQueryReqDTO;
import com.example.dto.resp.TicketPageQueryRespDTO;
import com.example.service.TicketService;
import com.example.service.cache.SeatMarginCacheLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final StationMapper stationMapper;
    private final TrainStationRelationMapper trainStationRelationMapper;
    private final TrainStationPriceMapper trainStationPriceMapper;
    private final TrainMapper trainMapper;
    private final SeatMapper seatMapper;
    private final SeatMarginCacheLoader seatMarginCacheLoader;

    /**
     * 查询接口 v1
     * @param requestParam
     * @return
     */
    // TODO 细节填充
    public final TicketPageQueryRespDTO pageListTicketQueryV1(TicketPageQueryReqDTO requestParam) {

        // 查表 t_staton
        LambdaQueryWrapper<StationDO> queryWrapper = Wrappers.lambdaQuery(StationDO.class)
                .eq(StationDO::getCode, requestParam.getToStation())
                .or()
                .eq(StationDO::getCode, requestParam.getFromStation());
        List<StationDO> stationDOList = stationMapper.selectList(queryWrapper);
        // 车票的结构集合
        List<TicketListDTO> seatResults = new ArrayList<>();
        // 查表 t_train_station_relation
        LambdaQueryWrapper<TrainStationRelationDO> trainStationRelationDOLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationRelationDO.class)
                .eq(TrainStationRelationDO::getStartRegion,stationDOList.get(0).getRegionName())
                .eq(TrainStationRelationDO::getEndRegion,stationDOList.get(1).getRegionName());
        List<TrainStationRelationDO> trainStationRelationDOList =  trainStationRelationMapper.selectList(trainStationRelationDOLambdaQueryWrapper);
        /**
         * 查询票价和余票
         */
        List<TrainStationPriceDO> trainStationPriceDOList = new ArrayList<>();
        List<SeatClassDTO> seatClassDTOList = new ArrayList<>();
        for(TrainStationRelationDO relationDO : trainStationRelationDOList) {
            // 查表 t_train
            LambdaQueryWrapper<TrainDO> trainDOLambdaQueryWrapper = Wrappers.lambdaQuery(TrainDO.class)
                    .eq(TrainDO::getId,relationDO.getTrainId());
            TrainDO trainDO = trainMapper.selectOne(trainDOLambdaQueryWrapper);
            // 包装TicketListDTO，List<TicketListDTO>
            TicketListDTO listDTO = new TicketListDTO();
            listDTO.setTrainId(String.valueOf(relationDO.getTrainId()));
            listDTO.setTrainNumber(trainDO.getTrainNumber());
            listDTO.setTrainBrand(trainDO.getTrainBrand());
            listDTO.setDeparture(relationDO.getDeparture());
            listDTO.setArrival(relationDO.getArrival());
            seatResults.add(listDTO);
        }

        for ( TicketListDTO ticketListDTO : seatResults ) {
            // 查表 t_train_station_price
            LambdaQueryWrapper<TrainStationPriceDO> trainStationPriceDOLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationPriceDO.class)
                    .eq(TrainStationPriceDO::getTrainId,ticketListDTO.getTrainId())
                    .eq(TrainStationPriceDO::getDeparture,ticketListDTO.getDeparture())
                    .eq(TrainStationPriceDO::getArrival,ticketListDTO.getArrival());
            List<TrainStationPriceDO> priceDOList = trainStationPriceMapper.selectList(trainStationPriceDOLambdaQueryWrapper);
            for (TrainStationPriceDO trainStationPriceDO : priceDOList) {
                SeatClassDTO seatClassDTO = new SeatClassDTO();
                seatClassDTO.setPrice(BigDecimal.valueOf(trainStationPriceDO.getPrice()));
                seatClassDTO.setType(trainStationPriceDO.getSeatType());
                seatClassDTO.setCandidate(false);
                Map<String,String> seatMarginMap = seatMarginCacheLoader.load(String.valueOf(trainStationPriceDO.getTrainId()),String.valueOf(trainStationPriceDO.getSeatType()), trainStationPriceDO.getDeparture(),trainStationPriceDO.getArrival());
                seatClassDTO.setQuantity(Integer.valueOf(seatMarginMap.get(trainStationPriceDO.getSeatType().toString())));
                seatClassDTOList.add(seatClassDTO);
            }
            ticketListDTO.setSeatClassList(seatClassDTOList);
        }
        return TicketPageQueryRespDTO.builder()
                .trainList(seatResults)
                .build();
    }
//        // 拼接redis key
//        String buildRegionTrainStationHashKey = String.format(REGION_TRAIN_STATION,stationDetails.get(0),stationDetails.get(1));
//
//        // 获取两城市所有车次信息
//        Map<Object,Object> regionTrainStationAllMap = stringRedisTemplate.opsForHash().entries(buildRegionTrainStationHashKey);
//
//        // 所有车次结果 （不含价格）
//        List<TicketListDTO> seatResults = regionTrainStationAllMap.values().stream().map(each -> JSON.parseObject(each.toString(),TicketListDTO.class)).toList();
//
//        // 按时间排序，代码省略
//
//        // 遍历获取车票价格和数量
//        for(TicketListDTO each : seatResults) {
//            // 获取价格
//            String ticketPrice = stringRedisTemplate.opsForValue().get(String.format(TRAIN_STATION_PRICE,each.getTrainId(),each.getDeparture(),each.getArrival()));
//            // 设置信息
//            List<TrainStationPriceDO> priceList = JSON.parseArray(ticketPrice, TrainStationPriceDO.class);
//            // 遍历列表获取车票余量
//            List<SeatClassDTO> seatList = new ArrayList<>();
//            for(TrainStationPriceDO seat : priceList) {
//                // 获取余量
//                Joiner join = Joiner.on("_");
//                String keySuffix = join.join(Lists.newArrayList( each.getTrainId() , each.getDeparture() , each.getArrival()));
//                Object ticketNum = stringRedisTemplate.opsForHash().get(TRAIN_STATION_REMAINING_TICKET + keySuffix,seat.getSeatType().toString());
//                // 设置信息
//                seatList.add(new SeatClassDTO(seat.getSeatType(),new BigDecimal(seat.getPrice()),Integer.parseInt(ticketNum.toString()),false));
//            }
//            each.setSeatClassList(seatList);
//        }
//
//        // return DTO
//        return seatResults;
//    }

    public Object purchaseTickets(Object param) {
        // 查余量，减库存
        return null;
    }

//    public Object executePurchaseTicket(PurchaseTicketReqDTO requestParam) {
//        /**
//         * 定位redis中对应车票余量key
//         * 加锁，-1
//          */
//        /**
//         * 加入订单模块，30min未付款进行回滚操作
//         */
//        return null;
//    }
}