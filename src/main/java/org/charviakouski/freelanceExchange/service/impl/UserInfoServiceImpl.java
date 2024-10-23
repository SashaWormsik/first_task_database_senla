package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final EntityMapper entityMapper;

    @Override
    public UserInfoDto insert(UserInfoDto userInfoDto) {
        log.info("insert new UserInfo with name {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        userInfo.getCredential().setUserInfo(userInfo);
        return entityMapper.fromEntityToDto(userInfoRepository.create(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto update(UserInfoDto userInfoDto) {
        log.info("update UserInfo with name {}", userInfoDto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        userInfo.getCredential().setUserInfo(userInfo);
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
        Optional<UserInfo> optionalUserInfo = userInfoRepository.getUserInfoByEmail(email);
        if (optionalUserInfo.isEmpty()) {
            log.info("userInfo with Email {} not found", email);
            throw new ServiceException("User not found");
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }
}
