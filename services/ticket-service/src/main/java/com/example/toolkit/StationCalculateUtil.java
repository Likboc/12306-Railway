package com.example.toolkit;

import com.example.dto.domain.RouteDTO;

import java.util.ArrayList;
import java.util.List;

public final class StationCalculateUtil {
    public static List<RouteDTO> throughStation(List<String> stations, String startStation, String endStation) {
        List<RouteDTO> routesToDeduct = new ArrayList<>();
        int startIndex = stations.indexOf(startStation);
        int endIndex = stations.indexOf(endStation);
        if (startIndex < 0 || endIndex < 0 || startIndex >= endIndex) {
            return routesToDeduct;
        }
        for (int i = startIndex; i < endIndex; i++) {
            for (int j = i + 1; j <= endIndex; j++) {
                String currentStation = stations.get(i);
                String nextStation = stations.get(j);
                RouteDTO routeDTO = new RouteDTO(currentStation, nextStation);
                routesToDeduct.add(routeDTO);
            }
        }
        return routesToDeduct;
    }
}
