package com.example.biz.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.biz.userservice.dao.entity.UserMailDO;
import com.example.biz.userservice.dao.entity.UserPhoneDO;
import com.example.biz.userservice.dao.mapper.UserMailMapper;
import com.example.biz.userservice.dao.mapper.UserPhoneMapper;
import com.example.biz.userservice.dto.req.UserLoginReqDTO;
import com.example.biz.userservice.dto.req.UserRegisterReqDTO;
import com.example.biz.userservice.dto.resp.UserLoginRespDTO;
import com.example.biz.userservice.dto.resp.UserRegisterRespDTO;
import com.example.biz.userservice.service.UserLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final UserMailMapper mapper;
    private final UserPhoneMapper userPhoneMapper;
    private StringRedisTemplate redisTemplate;

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO requestParam) {

        String UsernameOrMailOrPhone = requestParam.getUsernameOrMailOrPhone();
        LambdaQueryWrapper<UserMailDO> queryWrapper = Wrappers.lambdaQuery(UserMailDO.class)
                    .eq(UserMailDO::getMail,UsernameOrMailOrPhone);
        UserMailDO userMailDO = mapper.selectOne(queryWrapper);
        // TODO 生成JWT，设置redis
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserRegisterRespDTO register(UserRegisterReqDTO requestParam) {
        UserPhoneDO userPhoneDO = UserPhoneDO.builder()
                .id(Long.valueOf(1))
                .build();
        userPhoneMapper.insert(userPhoneDO);
        return null;
    }

    @Override
    public Object checkLogin(String accessToken) {

        return redisTemplate.opsForValue().get(accessToken);
    }

    @Override
    public void logout(String accessToken) {
        redisTemplate.delete(accessToken);
    }

}
