package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestExseption;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.UserInfoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final String ROLE_COMPANY = "COMPANY";
    private final String ROLE_EXECUTOR = "EXECUTOR";

    private final UserInfoRepository userInfoRepository;
    private final CredentialRepository credentialRepository;
    private final EntityMapper entityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDto insert(UserInfoDto userInfoDto) {
        log.info("insert new UserInfo with email {}", userInfoDto.getCredential().getEmail());
        if (credentialRepository.existsCredentialByEmail(userInfoDto.getCredential().getEmail())) {
            throw new BadCredentialsException("This email address already exists");
        }
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        userInfo.getCredential().setUserInfo(userInfo);
        userInfo.getCredential().setActive(true);
        userInfo.getCredential().setCreateDate(new Date());
        userInfo.getCredential().setPassword(passwordEncoder.encode(userInfoDto.getCredential().getPassword()));
        return entityMapper.fromEntityToDto(userInfoRepository.save(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto update(long id, UserInfoDto userInfoDto) {
        log.info("update UserInfo with Id {}", id);
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if (!credentialUserDetails.getId().equals(id)) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        UserInfo userInfoFromDB = userInfoRepository.findById(id)
                .orElseThrow(() -> new MyBadRequestExseption("userInfo not found with ID " + id));
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfoDto, UserInfo.class);
        userInfo.getCredential().setUserInfo(userInfo);
        userInfo.getCredential().setPassword(userInfoFromDB.getCredential().getPassword());
        return entityMapper.fromEntityToDto(userInfoRepository.save(userInfo), UserInfoDto.class);
    }

    @Override
    public UserInfoDto getById(Long id) {
        log.info("get userInfo with ID {}", id);
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(id);
        if (optionalUserInfo.isEmpty()) {
            log.info("userInfo with ID {} not found", id);
            throw new MyBadRequestExseption("userInfo not found with ID " + id);
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }

    @Override
    public Page<UserInfoDto> getAll(int page, int size, String sort) {
        log.info("get ALL userInfo");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return userInfoRepository
                .findAll(pageable)
                .map(user -> entityMapper.fromEntityToDto(user, UserInfoDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete userInfo with ID {}", id);
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if (!credentialUserDetails.getId().equals(id)) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        userInfoRepository.deleteById(id);
        return !userInfoRepository.existsById(id);
    }

    @Override
    public Page<UserInfoDto> getAllExecutorByLikeName(String userName, int page, int size, String sort) {
        log.info("get All UserInfo  with name {}", userName);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return userInfoRepository
                .findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameIn(userName, List.of(ROLE_EXECUTOR), pageable)
                .map(userI -> entityMapper.fromEntityToDto(userI, UserInfoDto.class));
    }

    @Override
    public UserInfoDto getUserInfoByEmail(String email) {
        log.info("get UserInfo  with email {}", email);
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findUserInfoByCredential_Email(email);
        if (optionalUserInfo.isEmpty()) {
            log.info("userInfo with Email {} not found", email);
            throw new MyBadRequestExseption("userInfo not found with email " + email);
        }
        return entityMapper.fromEntityToDto(optionalUserInfo.get(), UserInfoDto.class);
    }

    @Override
    public Page<UserInfoDto> getAllCompanyByLikeName(String userName, int page, int size) {
        log.info("get All Company by Like Name {}", userName);
        Pageable pageable = PageRequest.of(page - 1, size);
        return userInfoRepository.findAllUserInfoByNameContainingIgnoreCaseAndCredential_Role_NameIn(userName, List.of(ROLE_COMPANY), pageable)
                .map(userInfo -> entityMapper.fromEntityToDto(userInfo, UserInfoDto.class));
    }
}
