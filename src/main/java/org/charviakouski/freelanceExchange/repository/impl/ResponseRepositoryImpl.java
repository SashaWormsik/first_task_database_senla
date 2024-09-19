package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.springframework.stereotype.Component;

@Component
public class ResponseRepositoryImpl extends AbstractRepository<Long, Response> implements ResponseRepository {

    @Override
    protected Class<Response> getEntityClass() {
        return Response.class;
    }
}
