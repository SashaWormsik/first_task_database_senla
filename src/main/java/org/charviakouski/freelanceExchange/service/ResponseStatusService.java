package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;

import java.util.List;

public interface ResponseStatusService {
    List<ResponseStatusDto> getAll();

    ResponseStatusDto getById(Long id);

    ResponseStatusDto insert(ResponseStatusDto responseStatusDto);

    ResponseStatusDto update(ResponseStatusDto responseStatusDto);

    boolean delete(Long id);
}
