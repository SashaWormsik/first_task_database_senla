package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;

import java.util.List;

public interface CredentialService {
    List<CredentialDto> getAll();

    CredentialDto getById(Long id);

    CredentialDto insert(CredentialDto credentialDto);

    CredentialDto update(CredentialDto credentialDto);

    boolean delete(Long id);
}
