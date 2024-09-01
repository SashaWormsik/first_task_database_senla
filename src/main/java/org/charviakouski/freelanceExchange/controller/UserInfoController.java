package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        userInfoService.getAll();
        return null;
    }

    public String getById(String jsonUserInfoId) {
        userInfoService.getById(entityMapper.fromJsonToDto(jsonUserInfoId, UserInfoDto.class));
        return null;
    }

    public String insert(String jsonUserInfo) {
        userInfoService.insert(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
        return null;
    }

    public String update(String jsonUserInfo) {
        userInfoService.update(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
        return null;
    }

    public boolean delete(String jsonUserInfo) {
        userInfoService.delete(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
        return false;
    }
}
