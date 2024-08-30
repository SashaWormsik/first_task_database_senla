package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CredentialRepositoryImpl implements CredentialRepository {
    @Override
    public List<Credential> getAll() {
        return null;
    }

    @Override
    public Credential getById(Credential credential) {
        return null;
    }

    @Override
    public boolean insert(Credential credential) {
        return false;
    }

    @Override
    public boolean update(Credential credential) {
        return false;
    }

    @Override
    public boolean delete(Credential credential) {
        return false;
    }
}
