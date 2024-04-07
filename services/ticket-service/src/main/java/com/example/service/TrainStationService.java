package com.example.service;

import com.example.dto.domain.RouteDTO;

import java.util.List;

/**
 * 路线类
 */
public interface TrainStationService {
    Object listTrainStationQuery(String trainId);
    List<RouteDTO> listTrainStationRoute(String trainId, String departure, String arrival);
}
