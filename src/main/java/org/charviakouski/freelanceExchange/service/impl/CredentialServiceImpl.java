package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
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
    private EntityMapper entityMapper;

    @Override
    public List<CredentialDto> getAll() {
        return null;
    }

    @Override
    public CredentialDto getById(CredentialDto credentialDto) {
        return null;
    }

    @Override
    public CredentialDto insert(CredentialDto credentialDto) {
        return null;
    }

    @Override
    public CredentialDto update(CredentialDto credentialDto) {
        return null;
    }

    @Override
    public boolean delete(CredentialDto credentialDto) {
        return false;
    }
}
