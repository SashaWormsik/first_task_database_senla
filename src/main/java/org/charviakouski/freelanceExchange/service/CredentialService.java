package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CredentialService extends UserDetailsService {
    List<CredentialDto> getAll();

    CredentialDto getById(Long id);

    CredentialDto insert(CredentialDto credentialDto);

    CredentialDto update(CredentialDto credentialDto);

    boolean delete(Long id);
}
