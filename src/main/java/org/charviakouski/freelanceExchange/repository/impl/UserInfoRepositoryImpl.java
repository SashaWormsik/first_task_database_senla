package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoRepositoryImpl implements UserInfoRepository {
    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public UserInfo getById(UserInfo userInfo) {
        return null;
    }

    @Override
    public boolean insert(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean update(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean delete(UserInfo userInfo) {
        return false;
    }
}
