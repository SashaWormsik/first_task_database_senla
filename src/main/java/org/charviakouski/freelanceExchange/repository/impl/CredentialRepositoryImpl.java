package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CredentialRepositoryImpl implements CredentialRepository {
    @Override
    public List<Credential> getAll() {
        return null;
    }

    @Override
    public Optional<Credential> getById(Credential credential) {
        return null;
    }

    @Override
    public Credential insert(Credential credential) {
        return null;
    }

    @Override
    public Credential update(Credential newCredential, Credential oldCredential) {
        return null;
    }

    @Override
    public boolean delete(Credential credential) {
        return false;
    }
}
