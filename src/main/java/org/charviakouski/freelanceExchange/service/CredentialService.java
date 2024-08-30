package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;

import java.util.List;

public interface CredentialService {
    List<CredentialDto> getAll();

    CredentialDto getById(CredentialDto credentialDto);

    boolean delete(CredentialDto credentialDto);

    boolean insert(CredentialDto credentialDto);

    boolean update(CredentialDto credentialDto);
}
