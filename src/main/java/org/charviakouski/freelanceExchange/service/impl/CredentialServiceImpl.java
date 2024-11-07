package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final EntityMapper entityMapper;

    @Override
    public CredentialDto insert(CredentialDto credentialDto) {
        log.info("insert new Credential with email {}", credentialDto.getEmail());
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.save(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto update(CredentialDto credentialDto) {
        log.info("update Credential with email {}", credentialDto.getEmail());
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.save(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto getById(Long id) {
        Optional<Credential> optionalCredential = credentialRepository.findById(id);
        if (optionalCredential.isEmpty()) {
            log.info("credential with ID {} not found", id);
            throw new ServiceException("Credential not found");
        }
        return entityMapper.fromEntityToDto(optionalCredential.get(), CredentialDto.class);
    }

    @Override
    public List<CredentialDto> getAll() {
        log.info("get ALL credentials");
        return credentialRepository.findAll().stream()
                .map(credential -> entityMapper.fromEntityToDto(credential, CredentialDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete credential with ID {}", id);
        credentialRepository.deleteById(id);
        return !credentialRepository.existsById(id);
    }
}
