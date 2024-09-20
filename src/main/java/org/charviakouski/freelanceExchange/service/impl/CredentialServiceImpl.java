package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CredentialServiceImpl implements CredentialService {
    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public CredentialDto insert(CredentialDto credentialDto) {
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.create(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto update(CredentialDto credentialDto) {
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.update(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto getById(CredentialDto credentialDto) {
        Optional<Credential> optionalCredential = credentialRepository.getById(credentialDto.getId());
        if (optionalCredential.isEmpty()) {
            throw new ServiceException("Credential not found");
        }
        return entityMapper.fromEntityToDto(optionalCredential.get(), CredentialDto.class);
    }

    @Override
    public List<CredentialDto> getAll() {
        return credentialRepository.getAll().stream()
                .map(credential -> entityMapper.fromEntityToDto(credential, CredentialDto.class))
                .toList();
    }

    @Override
    public boolean delete(CredentialDto credentialDto) {
        credentialRepository.delete(credentialDto.getId());
        return credentialRepository.getById(credentialDto.getId()).isEmpty();
    }
}
