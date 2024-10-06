package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional
public class CredentialServiceImpl implements CredentialService {

    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public CredentialDto insert(CredentialDto credentialDto) {
        log.info("insert new Credential with email {}", credentialDto.getEmail());
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.create(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto update(CredentialDto credentialDto) {
        log.info("update Credential with email {}", credentialDto.getEmail());
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.update(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto getById(Long id) {
        Optional<Credential> optionalCredential = credentialRepository.getById(id);
        if (optionalCredential.isEmpty()) {
            log.info("credential with ID {} not found", id);
            throw new ServiceException("Credential not found");
        }
        return entityMapper.fromEntityToDto(optionalCredential.get(), CredentialDto.class);
    }

    @Override
    public List<CredentialDto> getAll() {
        log.info("get ALL credentials");
        return credentialRepository.getAll().stream()
                .map(credential -> entityMapper.fromEntityToDto(credential, CredentialDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete credential with ID {}", id);
        return credentialRepository.delete(id);
    }
}
