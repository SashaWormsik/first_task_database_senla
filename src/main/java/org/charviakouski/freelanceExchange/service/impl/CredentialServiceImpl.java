package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestException;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.charviakouski.freelanceExchange.util.PrincipalUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityMapper entityMapper;
    private final PrincipalUtil principalUtil;

    @Override
    public CredentialDto insert(CredentialDto credentialDto) {
        log.info("Insert new credential with email {}", credentialDto.getEmail());
        if (credentialRepository.existsCredentialByEmail(credentialDto.getEmail())) {
            throw new BadCredentialsException("This email address already exists");
        }
        UserInfo userInfo = new UserInfo();
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        credential.setPassword(passwordEncoder.encode(credentialDto.getPassword()));
        credential.setUserInfo(userInfo);
        userInfo.setCredential(credential);
        return entityMapper.fromEntityToDto(credentialRepository.save(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto update(long id, CredentialDto credentialDto) {
        log.info("Update credential with email {}", credentialDto.getEmail());
        if (!credentialRepository.existsById(id)) {
            throw new MyBadRequestException("Credential with id " + id + " does not exist");
        }
        if (!principalUtil.checkId(id)) {
            throw new AccessDeniedException("You cannot change other people's login details");
        }
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        credential.setPassword(passwordEncoder.encode(credentialDto.getPassword()));
        return entityMapper.fromEntityToDto(credentialRepository.save(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto getById(Long id) {
        log.info("Update credential with ID {}", id);
        if (!principalUtil.checkId(id)) {
            throw new AccessDeniedException("You cannot view other people's login information");
        }
        Credential credential = credentialRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Credential with ID {} not found", id);
                    return new MyBadRequestException("Credential not found with id " + id);
                });
        return entityMapper.fromEntityToDto(credential, CredentialDto.class);
    }

    @Override
    public Page<CredentialDto> getAll(int page, int size, String sort) {
        log.info("Get ALL credentials");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return credentialRepository
                .findAll(pageable)
                .map(credential -> entityMapper.fromEntityToDto(credential, CredentialDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete credential with ID {}", id);
        if (!principalUtil.checkId(id)) {
            throw new AccessDeniedException("You cannot delete other people's login information");
        }
        credentialRepository.deleteById(id);
        return !credentialRepository.existsById(id);
    }
}
