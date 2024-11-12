package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.springframework.data.domain.Page;

public interface UserInfoService {
    Page<UserInfoDto> getAll(int page, int size, String sort);

    UserInfoDto getById(Long id);

    UserInfoDto insert(UserInfoDto userInfoDto);

    UserInfoDto update(long id, UserInfoDto userInfoDto);

    boolean delete(Long id);

    Page<UserInfoDto> getAllUserInfoByName(String userName, int page, int size, String sort);

    UserInfoDto getUserInfoByEmail(String email);

    Page<UserInfoDto> getAllCompanyByLikeName(String userName, int page, int size);
}
