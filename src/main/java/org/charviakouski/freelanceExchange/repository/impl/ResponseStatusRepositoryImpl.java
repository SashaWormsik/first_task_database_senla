package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResponseStatusRepositoryImpl implements ResponseStatusRepository {
    @Override
    public List<ResponseStatus> getAll() {
        return null;
    }

    @Override
    public Optional<ResponseStatus> getById(ResponseStatus responseStatus) {
        return null;
    }

    @Override
    public ResponseStatus insert(ResponseStatus responseStatus) {
        return null;
    }

    @Override
    public ResponseStatus update(ResponseStatus newResponseStatus, ResponseStatus oldResponseStatus) {
        return null;
    }

    @Override
    public boolean delete(ResponseStatus responseStatus) {
        return false;
    }
}
