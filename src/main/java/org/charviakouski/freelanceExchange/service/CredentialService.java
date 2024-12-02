package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.springframework.data.domain.Page;

public interface CredentialService {
    Page<CredentialDto> getAll(int page, int size, String sort);

    CredentialDto getById(Long id);

    CredentialDto insert(CredentialDto credentialDto);

    CredentialDto update(long id, CredentialDto credentialDto);

    boolean delete(Long id);
}
