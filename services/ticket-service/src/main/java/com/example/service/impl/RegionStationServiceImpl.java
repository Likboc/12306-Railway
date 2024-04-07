package com.example.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.dao.mapper.StationMapper;
import com.example.service.RegionStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegionStationServiceImpl implements RegionStationService {
    private final StationMapper stationMapper;
    public Object selectList(){
        return stationMapper.selectList(Wrappers.emptyWrapper());
    }
}
