package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;

import java.util.List;

public interface ResponseRepository extends DefaultRepository<Long, Response> {
    List<Response> getAllResponsesByExecutor(UserInfo userInfo);
}
