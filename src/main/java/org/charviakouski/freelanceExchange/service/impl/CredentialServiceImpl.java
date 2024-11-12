package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.charviakouski.freelanceExchange.service.CredentialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository credentialRepository;
    private final PasswordEncoder passwordEncoder;
    private final EntityMapper entityMapper;

    @Override
    public CredentialDto insert(CredentialDto credentialDto) {
        log.info("insert new Credential with email {}", credentialDto.getEmail());
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        return entityMapper.fromEntityToDto(credentialRepository.save(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto update(long id, CredentialDto credentialDto) {
        log.info("update Credential with email {}", credentialDto.getEmail());
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if(!credentialUserDetails.getId().equals(id)){
            throw new AccessDeniedException("You cannot change other people's data");
        }
        Credential credential = entityMapper.fromDtoToEntity(credentialDto, Credential.class);
        credential.setPassword(passwordEncoder.encode(credentialDto.getPassword()));
        return entityMapper.fromEntityToDto(credentialRepository.save(credential), CredentialDto.class);
    }

    @Override
    public CredentialDto getById(Long id) {
        log.info("update Credential with ID {}", id);
        Optional<Credential> optionalCredential = credentialRepository.findById(id);
        if (optionalCredential.isEmpty()) {
            log.info("credential with ID {} not found", id);
            throw new ServiceException("Credential not found");
        }
        return entityMapper.fromEntityToDto(optionalCredential.get(), CredentialDto.class);
    }

    @Override
    public Page<CredentialDto> getAll(int page, int size, String sort) {
        log.info("get ALL credentials");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return credentialRepository
                .findAll(pageable)
                .map(credential -> entityMapper.fromEntityToDto(credential, CredentialDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete credential with ID {}", id);
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if(!credentialUserDetails.getId().equals(id)){
            throw new AccessDeniedException("You cannot change other people's data");
        }
        credentialRepository.deleteById(id);
        return !credentialRepository.existsById(id);
    }
}
