package com.example.biz.userservice.service;

import com.example.biz.userservice.dto.req.UserLoginReqDTO;
import com.example.biz.userservice.dto.req.UserRegisterReqDTO;
import com.example.biz.userservice.dto.resp.UserLoginRespDTO;
import com.example.biz.userservice.dto.resp.UserRegisterRespDTO;

public interface UserLoginService {
    UserLoginRespDTO login(UserLoginReqDTO requestParam);

    void logout(String requestParam);

    Object checkLogin(String accessToken);

    UserRegisterRespDTO register(UserRegisterReqDTO requestParam);
}
