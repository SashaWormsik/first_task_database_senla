package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseStatusRepositoryImpl implements ResponseStatusRepository {
    @Override
    public List<ResponseStatus> getAll() {
        return null;
    }

    @Override
    public ResponseStatus getById(ResponseStatus responseStatus) {
        return null;
    }

    @Override
    public boolean insert(ResponseStatus responseStatus) {
        return false;
    }

    @Override
    public boolean update(ResponseStatus responseStatus) {
        return false;
    }

    @Override
    public boolean delete(ResponseStatus responseStatus) {
        return false;
    }
}
