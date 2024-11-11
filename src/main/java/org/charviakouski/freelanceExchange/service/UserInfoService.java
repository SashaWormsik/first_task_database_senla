package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.springframework.data.domain.Page;

public interface UserInfoService {
    Page<UserInfoDto> getAll(int page, int size, String sort);

    UserInfoDto getById(Long id);

    UserInfoDto insert(CredentialDto credentialDto);

    UserInfoDto update(long id, UserInfoDto userInfoDto);

    boolean delete(Long id);

    Page<UserInfoDto> getAllUserInfoByName(String userName, int page, int size, String sort);

    UserInfoDto getUserInfoByEmail(String email);
}
