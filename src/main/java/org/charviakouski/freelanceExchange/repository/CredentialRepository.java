package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Credential;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository {
    List<Credential> getAll();

    Optional<Credential> getById(Credential credential);

    Credential insert(Credential credential);

    Credential update(Credential newCredential, Credential oldCredential);

    boolean delete(Credential credential);
}
