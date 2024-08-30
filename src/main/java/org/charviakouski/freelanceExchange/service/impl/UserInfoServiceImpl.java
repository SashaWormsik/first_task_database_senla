package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<UserInfoDto> getAll() {
        return null;
    }

    @Override
    public UserInfoDto getById(UserInfoDto userInfoDto) {
        return null;
    }

    @Override
    public boolean insert(UserInfoDto userInfoDto) {
        return false;
    }

    @Override
    public boolean update(UserInfoDto userInfoDto) {
        return false;
    }

    @Override
    public boolean delete(UserInfoDto userInfoDto) {
        return false;
    }
}
