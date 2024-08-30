package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.util.List;

public interface UserInfoService {
    List<UserInfoDto> getAll();

    UserInfoDto getById(UserInfoDto userInfoDto);

    boolean insert(UserInfoDto userInfoDto);

    boolean update(UserInfoDto userInfoDto);

    boolean delete(UserInfoDto userInfoDto);
}
