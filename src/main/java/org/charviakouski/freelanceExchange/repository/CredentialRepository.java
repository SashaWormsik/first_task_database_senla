package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Credential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Long> {

    Optional<Credential> findCredentialByEmail(String email);

    Optional<Credential> findCredentialByEmailAndActiveTrue(String username);

    boolean existsCredentialByEmail(String email);
}
