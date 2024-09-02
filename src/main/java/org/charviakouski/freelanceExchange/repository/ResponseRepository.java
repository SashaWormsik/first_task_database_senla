package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Response;

import java.util.List;
import java.util.Optional;

public interface ResponseRepository {
    List<Response> getAll();

    Optional<Response> getById(Response response);

    Response insert(Response response);

    Response update(Response newResponse, Response oldResponse);

    boolean delete(Response response);
}
