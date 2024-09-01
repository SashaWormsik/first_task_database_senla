package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;

import java.util.List;
import java.util.Optional;

public interface ResponseStatusRepository {
    List<ResponseStatus> getAll();

    Optional<ResponseStatus> getById(ResponseStatus responseStatus);

    ResponseStatus insert(ResponseStatus responseStatus);

    ResponseStatus update(ResponseStatus newResponseStatus, ResponseStatus oldResponseStatus);

    boolean delete(ResponseStatus responseStatus);
}
