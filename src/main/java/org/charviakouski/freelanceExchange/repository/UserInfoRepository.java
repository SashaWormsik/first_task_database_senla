package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;

import java.util.List;

public interface UserInfoRepository {
    List<UserInfo> getAll();

    UserInfo getById(UserInfo userInfo);

    boolean insert(UserInfo userInfo);

    boolean update(UserInfo userInfo);

    boolean delete(UserInfo userInfo);
}
