package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseRepositoryImpl implements ResponseRepository {
    @Override
    public List<Response> getAll() {
        return null;
    }

    @Override
    public Response getById(Response response) {
        return null;
    }

    @Override
    public boolean insert(Response response) {
        return false;
    }

    @Override
    public boolean update(Response response) {
        return false;
    }

    @Override
    public boolean delete(Response response) {
        return false;
    }
}
