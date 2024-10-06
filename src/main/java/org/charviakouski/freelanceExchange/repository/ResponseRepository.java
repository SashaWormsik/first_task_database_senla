package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;

import java.util.List;

public interface ResponseRepository extends CrudRepository<Long, Response> {
    List<Response> getAllResponsesByExecutor(Long id);
}
