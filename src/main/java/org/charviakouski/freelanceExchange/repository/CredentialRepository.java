package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.dto.CredentialDto;
import org.charviakouski.freelanceExchange.model.entity.Credential;

import java.util.List;

public interface CredentialRepository {
    List<Credential> getAll();

    Credential getById(Credential credential);

    boolean insert(Credential credential);

    boolean update(Credential credential);

    boolean delete(Credential credential);
}
