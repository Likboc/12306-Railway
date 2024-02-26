package com.example.gateway.config;

import lombok.Data;

import java.util.List;

@Data
public class Config {

    /**
     * 黑名单前置路径，需要验证token的url
     */
    private List<String> blackPathPre;
}
