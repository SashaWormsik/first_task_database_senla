package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;

import java.util.List;

public interface ResponseStatusRepository {
    List<ResponseStatus> getAll();

    ResponseStatus getById(ResponseStatus responseStatus);

    boolean insert(ResponseStatus responseStatus);

    boolean update(ResponseStatus responseStatus);

    boolean delete(ResponseStatus responseStatus);
}
