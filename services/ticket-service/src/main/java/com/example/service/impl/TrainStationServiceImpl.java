package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.entity.TrainStationDO;
import com.example.dao.mapper.TrainStationMapper;
import com.example.dto.domain.RouteDTO;
import com.example.service.TrainStationService;
import com.example.toolkit.StationCalculateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainStationServiceImpl implements TrainStationService {

    private final TrainStationMapper trainStationMapper;

    @Override
    public Object listTrainStationQuery(String trainId) {
        LambdaQueryWrapper<TrainStationDO> trainStationDOLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationDO.class)
                .eq(TrainStationDO::getTrainId,trainId);
        return trainStationMapper.selectList(trainStationDOLambdaQueryWrapper);
    }

    @Override
    public List<RouteDTO> listTrainStationRoute(String trainId, String departure, String arrival) {
        // 查表 t_train_station
        LambdaQueryWrapper<TrainStationDO> trainStationDOLambdaQueryWrapper = Wrappers.lambdaQuery(TrainStationDO.class)
                .eq(TrainStationDO::getTrainId,trainId)
                .select(TrainStationDO::getDeparture);
        List<TrainStationDO> trainStationDOList = trainStationMapper.selectList(trainStationDOLambdaQueryWrapper);
        List<String> trainStationAllList = trainStationDOList.stream().map(TrainStationDO::getDeparture).collect(Collectors.toList());
        return StationCalculateUtil.throughStation(trainStationAllList,departure,arrival);
    }
}
