package com.example.controller;

import com.example.Results;
import com.example.result.Result;
import com.example.service.RegionStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 地区和车站查询控制层
 */
@RestController
@RequiredArgsConstructor
public class RegionStationController {
    private final RegionStationService regionStationService;
    /**
     * 获取车站所有信息集合
     * 用于Ajax下拉菜单
     */
    @GetMapping("/api/ticket-service/station/all")
    public Result<Object> listAllStation() {
        return Results.success(regionStationService.selectList());
    }
}
