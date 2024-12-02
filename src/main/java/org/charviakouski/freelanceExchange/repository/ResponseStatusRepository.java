package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseStatusRepository extends JpaRepository<ResponseStatus, Long> {

    ResponseStatus findByStatus(String status);
}
