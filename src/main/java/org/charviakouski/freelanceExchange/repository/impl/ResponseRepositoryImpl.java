package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResponseRepositoryImpl implements ResponseRepository {
    @Override
    public List<Response> getAll() {
        return null;
    }

    @Override
    public Optional<Response> getById(Response response) {
        return null;
    }

    @Override
    public Response insert(Response response) {
        return null;
    }

    @Override
    public Response update(Response newResponse, Response oldResponse) {
        return null;
    }

    @Override
    public boolean delete(Response response) {
        return false;
    }
}
