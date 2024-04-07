package com.example.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RouteDTO {
    private String startStation;
    private String endStation;
}
