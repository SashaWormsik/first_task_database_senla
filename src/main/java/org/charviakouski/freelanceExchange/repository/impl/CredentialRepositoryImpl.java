package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.springframework.stereotype.Component;

@Component
public class CredentialRepositoryImpl extends AbstractRepository<Long, Credential> implements CredentialRepository {

    @Override
    protected Class<Credential> getEntityClass() {
        return Credential.class;
    }
}
