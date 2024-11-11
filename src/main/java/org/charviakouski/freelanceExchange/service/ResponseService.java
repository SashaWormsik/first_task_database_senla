package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResponseService {
    Page<ResponseDto> getAll(int page, int size, String sort);

    ResponseDto getById(Long id);

    ResponseDto insert(ResponseDto responseDto);

    ResponseDto update(ResponseDto responseDto);

    boolean delete(Long id);

    List<ResponseDto> getAllResponsesByExecutor(Long id);
}
