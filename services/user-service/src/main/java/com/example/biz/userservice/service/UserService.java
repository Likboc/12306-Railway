package com.example.biz.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.biz.userservice.dao.entity.UserDO;
import com.example.biz.userservice.dto.req.UserLoginReqDTO;
import com.example.biz.userservice.dto.resp.UserQueryRespDTO;
import org.apache.catalina.User;

public interface UserService {

    public UserDO getUserInfo(UserLoginReqDTO userLoginReqDTO);

    public boolean checkUserInfo(UserLoginReqDTO userLoginReqDTO);

}
