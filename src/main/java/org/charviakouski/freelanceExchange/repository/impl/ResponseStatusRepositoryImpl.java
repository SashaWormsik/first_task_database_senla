package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.springframework.stereotype.Component;

@Component
public class ResponseStatusRepositoryImpl extends AbstractRepository<Long, ResponseStatus> implements ResponseStatusRepository {

    @Override
    protected Class<ResponseStatus> getEntityClass() {
        return ResponseStatus.class;
    }
}
