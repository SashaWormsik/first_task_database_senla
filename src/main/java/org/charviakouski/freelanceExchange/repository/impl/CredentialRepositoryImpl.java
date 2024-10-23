package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.TypedQuery;
import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.CredentialRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CredentialRepositoryImpl extends AbstractRepository<Long, Credential> implements CredentialRepository {

    @Override
    protected Class<Credential> getEntityClass() {
        return Credential.class;
    }

    @Override
    public Optional<Credential> getCredentialByEmail(String email) {
        String jpqlQuery = "SELECT c FROM Credential c JOIN FETCH c.role r WHERE c.email = :email";
        TypedQuery<Credential> query = entityManager.createQuery(jpqlQuery, Credential.class);
        query.setParameter("email", email);
        Credential result = query.getSingleResult();
        return Optional.ofNullable(result);
    }
}
