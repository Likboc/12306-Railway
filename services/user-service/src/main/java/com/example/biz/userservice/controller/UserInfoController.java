package com.example.biz.userservice.controller;

import com.example.Results;
import com.example.biz.userservice.dao.entity.UserDO;
import com.example.biz.userservice.dto.req.UserLoginReqDTO;
import com.example.biz.userservice.dto.req.UserRegisterReqDTO;
import com.example.biz.userservice.dto.resp.UserRegisterRespDTO;
import com.example.biz.userservice.service.UserLoginService;
import com.example.biz.userservice.service.UserService;
import com.example.result.Result;
import io.micrometer.core.lang.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoController {

    private final UserService userService;
    private final UserLoginService userLoginService;

    /**
     * 根据用户账号查询信息
     */
    @GetMapping()
    public Result<UserDO> getUserInfoByUserName(UserLoginReqDTO reqDTO) {
        return Results.success(userService.getUserInfo(reqDTO));
    }

    /**
     * 查询用户是否存在
     */
    public Result<Boolean> checkUserInfo(UserLoginReqDTO userLoginReqDTO) {
        return Results.success(userService.checkUserInfo(userLoginReqDTO));
    }
    /**
     * 用户注册
     */
    @PostMapping("/api/user-service/register")
    public Result<UserRegisterRespDTO> register(@RequestBody @Nullable UserRegisterReqDTO requestParam) {
        return Results.success(userLoginService.register(requestParam));
    }
}
