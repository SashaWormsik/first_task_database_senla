package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {
    List<UserInfoDto> getAll();

    UserInfoDto getById(UserInfoDto userInfoDto);

    UserInfoDto insert(UserInfoDto userInfoDto);

    UserInfoDto update(UserInfoDto userInfoDto);

    boolean delete(UserInfoDto userInfoDto);

    List<UserInfoDto> getAllUserInfoByName(UserInfoDto userInfoDto);

    UserInfoDto getUserInfoByEmail(UserInfoDto userInfoDto);
}
