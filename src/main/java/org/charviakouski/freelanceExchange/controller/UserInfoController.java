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
        return entityMapper.fromDtoToJson(userInfoService.getAll());
    }

    public String getById(String jsonUserInfoId) {
        UserInfoDto userInfoDto = userInfoService.getById(entityMapper.fromJsonToDto(jsonUserInfoId, UserInfoDto.class));
        return entityMapper.fromDtoToJson(userInfoDto);
    }

    public String insert(String jsonUserInfo) {
        UserInfoDto userInfoDto = userInfoService.insert(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
        return entityMapper.fromDtoToJson(userInfoDto);
    }

    public String update(String jsonUserInfo) {
        UserInfoDto userInfoDto = userInfoService.update(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
        return entityMapper.fromDtoToJson(userInfoDto);
    }

    public boolean delete(String jsonUserInfo) {
        return userInfoService.delete(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
    }

    public String getAllUserInfoByName(String jsonUserInfoName) {
        UserInfoDto userInfoDto = entityMapper.fromJsonToDto(jsonUserInfoName, UserInfoDto.class);
        return entityMapper.fromDtoToJson(userInfoService.getAllUserInfoByName(userInfoDto));
    }

    public String getUserInfoByEmail(String jsonUserInfoEmail) {
        UserInfoDto userInfoDto = entityMapper.fromJsonToDto(jsonUserInfoEmail, UserInfoDto.class);
        return entityMapper.fromDtoToJson(userInfoService.getUserInfoByEmail(userInfoDto));
    }
}
