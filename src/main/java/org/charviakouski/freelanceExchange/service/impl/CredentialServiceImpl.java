package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CredentialServiceImpl implements CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    EntityMapper entityMapper;

    @Override
    public List<CredentialDto> getAll() {
        credentialRepository.getAll();
        return null;
    }

    @Override
    public CredentialDto getById(CredentialDto credentialDto) {
        credentialRepository.getById(entityMapper.fromDtoToEntity(credentialDto, Credential.class));
        return null;
    }

    @Override
    public boolean insert(CredentialDto credentialDto) {
        credentialRepository.insert(entityMapper.fromDtoToEntity(credentialDto, Credential.class));
        return false;
    }

    @Override
    public boolean update(CredentialDto credentialDto) {
        credentialRepository.update(entityMapper.fromDtoToEntity(credentialDto, Credential.class));
        return false;
    }

    @Override
    public boolean delete(CredentialDto credentialDto) {
        credentialRepository.delete(entityMapper.fromDtoToEntity(credentialDto, Credential.class));
        return false;
    }
}
