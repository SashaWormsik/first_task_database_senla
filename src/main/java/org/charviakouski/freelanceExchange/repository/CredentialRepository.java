package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Credential;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<Long, Credential> {

    Optional<Credential> getCredentialByEmail(String email);
}
