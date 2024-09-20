package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends DefaultRepository<Long, UserInfo> {

    List<UserInfo> getAllUserInfoByName(String username);
    Optional<UserInfo> getUserInfoByEmail(String email);
}
