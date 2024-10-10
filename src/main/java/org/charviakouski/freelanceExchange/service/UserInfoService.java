package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {
    List<UserInfoDto> getAll();

    UserInfoDto getById(Long id);

    UserInfoDto insert(UserInfoDto userInfoDto);

    UserInfoDto update(UserInfoDto userInfoDto);

    boolean delete(Long id);

    List<UserInfoDto> getAllUserInfoByName(String userName);

    UserInfoDto getUserInfoByEmail(String email);
}
