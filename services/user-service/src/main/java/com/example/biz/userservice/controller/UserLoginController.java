package com.example.biz.userservice.controller;

import com.example.Results;
import com.example.biz.userservice.dto.req.UserLoginReqDTO;
import com.example.biz.userservice.dto.resp.UserLoginRespDTO;
import com.example.biz.userservice.service.UserLoginService;
import com.example.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserLoginController {

    private UserLoginService userLoginService;

    /**
     * 实现用户登录
     * @param requestParam
     * @return
     */
    @PostMapping("/api/user-service/v1/login")
    public Result<UserLoginRespDTO> login(UserLoginReqDTO requestParam) {
        return Results.success(userLoginService.login(requestParam));
    }

    /**
     * 通过 Token 检查用户是否登录
     */
    @GetMapping("/api/user-service/check-login")
    public Result<Object> checkLogin(@RequestParam("accessToken") String accessToken) {
        Object result = userLoginService.checkLogin(accessToken);
        return Results.success(result);
    }

    /**
     * 用户退出登录
     */
    @GetMapping("/api/user-service/logout")
    public Result<Void> logout(@RequestParam(required = false) String accessToken) {
        userLoginService.logout(accessToken);
        return Results.success();
    }

}
