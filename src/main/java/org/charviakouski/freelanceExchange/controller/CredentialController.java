package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CredentialController {
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        credentialService.getAll();
        return null;
    }

    public String getById(String jsonCredentialId) {
        credentialService.getById(entityMapper.fromJsonToDto(jsonCredentialId, CredentialDto.class));
        return null;
    }

    public String insert(String jsonCredential) {
        credentialService.insert(entityMapper.fromJsonToDto(jsonCredential, CredentialDto.class));
        return null;
    }

    public String update(String jsonCredential) {
        credentialService.update(entityMapper.fromJsonToDto(jsonCredential, CredentialDto.class));
        return null;
    }

    public boolean delete(String jsonCredential) {
        credentialService.delete(entityMapper.fromJsonToDto(jsonCredential, CredentialDto.class));
        return false;
    }
}
