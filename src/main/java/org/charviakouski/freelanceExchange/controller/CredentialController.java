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
        return entityMapper.fromDtoToJson(credentialService.getAll());
    }

    public String getById(String jsonCredentialId) {
        CredentialDto credentialDto = credentialService.getById(entityMapper.fromJsonToDto(jsonCredentialId, CredentialDto.class));
        return entityMapper.fromDtoToJson(credentialDto);
    }

    public String insert(String jsonCredential) {
        CredentialDto credentialDto = credentialService.insert(entityMapper.fromJsonToDto(jsonCredential, CredentialDto.class));
        return entityMapper.fromDtoToJson(credentialDto);
    }

    public String update(String jsonCredential) {
        CredentialDto credentialDto = credentialService.update(entityMapper.fromJsonToDto(jsonCredential, CredentialDto.class));
        return entityMapper.fromDtoToJson(credentialDto);
    }

    public boolean delete(String jsonCredential) {
        return credentialService.delete(entityMapper.fromJsonToDto(jsonCredential, CredentialDto.class));
    }
}
