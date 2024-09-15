package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public UserInfoDto insert(UserInfoDto userInfoDto) {
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        if (userInfoRepository.getById(userInfo).isPresent()) {
            throw new RuntimeException("Уже есть такой объект");
        }
        return entityMapper.fromEntityToDto(userInfoRepository.insert(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto getById(UserInfoDto userInfoDto) {
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getById(userInfo);
        if (!optionalUserInfo.isPresent()) {
            throw new RuntimeException("Объект не существует!!!");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }

    @Override
    public UserInfoDto update(UserInfoDto userInfoDto) {
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getById(userInfo);
        if(!optionalUserInfo.isPresent()) {
            throw new RuntimeException("Объект отсутствует, а значит обновить невозможно");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }

    @Override
    public List<UserInfoDto> getAll() {
        return null;
    }

    @Override
    public boolean delete(UserInfoDto userInfoDto) {
        return false;
    }
}
