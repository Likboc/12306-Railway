package com.example.biz.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.biz.userservice.dao.entity.UserDO;
import com.example.biz.userservice.dao.mapper.UserMapper;
import com.example.biz.userservice.dto.req.UserLoginReqDTO;
import com.example.biz.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userQueryMapper;

    @Override
    public UserDO getUserInfo(UserLoginReqDTO userLoginReqDTO) {
        LambdaQueryWrapper<UserDO> wrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername,userLoginReqDTO.getUsernameOrMailOrPhone());
        return userQueryMapper.selectOne(wrapper);
    }

    @Override
    public boolean checkUserInfo(UserLoginReqDTO userLoginReqDTO) {
        UserDO userDO = getUserInfo(userLoginReqDTO);
        return userDO == null;
    }
}
