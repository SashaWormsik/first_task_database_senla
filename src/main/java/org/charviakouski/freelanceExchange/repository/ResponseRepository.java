package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Response;

import java.util.List;

public interface ResponseRepository {
    List<Response> getAll();

    Response getById(Response response);

    boolean insert(Response response);

    boolean update(Response response);

    boolean delete(Response response);
}
