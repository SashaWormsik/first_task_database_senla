package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public UserInfoDto insert(UserInfoDto userInfoDto) {
        log.info("insert new UserInfo with name {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        return entityMapper.fromEntityToDto(userInfoRepository.create(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto update(UserInfoDto userInfoDto) {
        log.info("update UserInfo with name {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        return entityMapper.fromEntityToDto(userInfoRepository.update(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto getById(Long id) {
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getById(id);
        if (optionalUserInfo.isEmpty()) {
            log.info("userInfo with ID {} not found", id);
            throw new ServiceException("User not found");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }

    @Override
    public List<UserInfoDto> getAll() {
        log.info("get ALL userInfo");
        return userInfoRepository.getAll().stream()
                .map(userInfo -> entityMapper.fromEntityToDto(userInfo, UserInfoDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete userInfo with ID {}", id);
        return userInfoRepository.delete(id);
    }

    @Override
    public List<UserInfoDto> getAllUserInfoByName(String userName) {
        log.info("get All UserInfo  with name {}", userName);
        List<UserInfo> userInfoList = userInfoRepository.getAllUserInfoByName(userName);
        return userInfoList.stream()
                .map(userI -> entityMapper.fromEntityToDto(userI, UserInfoDto.class))
                .toList();
    }

    @Override
    public UserInfoDto getUserInfoByEmail(String email) {
        ;
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getUserInfoByEmail(email);
        if (optionalUserInfo.isEmpty()) {
            log.info("userInfo with Email {} not found", email);
            throw new ServiceException("User not found");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }
}
