package com.example.controller;

import com.example.service.TrainStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TrainStationController {

    private final TrainStationService trainStationService;
    @GetMapping()
    public Object listTrainStationQuery(String trainId) {
        return trainStationService.listTrainStationQuery(trainId);
    }
}