package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserInfoRepository {
    boolean isUserInfoPresentByName(UserInfo userInfo);

    List<UserInfo> getAll();

    Optional<UserInfo> getById(UserInfo userInfo);

    UserInfo insert(UserInfo userInfo);

    UserInfo update(UserInfo newUserInfo, UserInfo oldUserInfo);

    boolean delete(UserInfo userInfo);
}
