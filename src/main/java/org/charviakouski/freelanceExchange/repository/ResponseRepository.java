package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> getAllResponsesByExecutor_Id(Long id);
}
