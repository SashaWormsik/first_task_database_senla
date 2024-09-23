package org.charviakouski.freelanceExchange.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public UserInfoDto insert(UserInfoDto userInfoDto) {
        LOGGER.log(Level.INFO, "insert new UserInfo with name {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        return entityMapper.fromEntityToDto(userInfoRepository.create(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto update(UserInfoDto userInfoDto) {
        LOGGER.log(Level.INFO, "update UserInfo with name {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        return entityMapper.fromEntityToDto(userInfoRepository.update(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto getById(UserInfoDto userInfoDto) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getById(userInfoDto.getId());
        if (optionalUserInfo.isEmpty()) {
            LOGGER.log(Level.INFO, "userInfo with ID {} not found", userInfoDto.getId());
            throw new ServiceException("User not found");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }

    @Override
    public List<UserInfoDto> getAll() {
        LOGGER.log(Level.INFO, "get ALL userInfo");
        return userInfoRepository.getAll().stream()
                .map(userInfo -> entityMapper.fromEntityToDto(userInfo, UserInfoDto.class))
                .toList();
    }

    @Override
    public boolean delete(UserInfoDto userInfoDto) {
        LOGGER.log(Level.INFO, "delete userInfo with name {}", userInfoDto.getName());
        userInfoRepository.delete(userInfoDto.getId());
        return userInfoRepository.getById(userInfoDto.getId()).isEmpty();
    }

    @Override
    public List<UserInfoDto> getAllUserInfoByName(UserInfoDto userInfoDto) {
        LOGGER.log(Level.INFO, "get All UserInfo  with name {}", userInfoDto.getName());
        List<UserInfo> userInfoList = userInfoRepository.getAllUserInfoByName(userInfoDto.getName());
        return userInfoList.stream()
                .map(userI -> entityMapper.fromEntityToDto(userI, UserInfoDto.class))
                .toList();
    }

    @Override
    public UserInfoDto getUserInfoByEmail(UserInfoDto userInfoDto) {
        String email = userInfoDto.getCredential().getEmail();
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getUserInfoByEmail(email);
        if (optionalUserInfo.isEmpty()) {
            LOGGER.log(Level.INFO, "userInfo with Email {} not found", email);
            throw new ServiceException("User not found");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }
}
