package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public Optional<UserInfo> getById(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo insert(UserInfo userInfo) {
        return null;
    }

    @Override
    public UserInfo update(UserInfo newUserInfo, UserInfo oldUserInfo) {
        return null;
    }

    @Override
    public boolean delete(UserInfo userInfo) {
        return false;
    }
}
