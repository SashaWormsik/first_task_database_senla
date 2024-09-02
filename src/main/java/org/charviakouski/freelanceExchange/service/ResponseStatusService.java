package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;

import java.util.List;

public interface ResponseStatusService {
    List<ResponseStatusDto> getAll();

    ResponseStatusDto getById(ResponseStatusDto responseStatusDto);

    ResponseStatusDto insert(ResponseStatusDto responseStatusDto);

    ResponseStatusDto update(ResponseStatusDto responseStatusDto);

    boolean delete(ResponseStatusDto responseStatusDto);
}
