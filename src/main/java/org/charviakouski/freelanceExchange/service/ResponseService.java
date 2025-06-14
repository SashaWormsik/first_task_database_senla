package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResponseService {
    Page<ResponseDto> getAll(int page, int size, String sort);

    ResponseDto getById(Long id);

    ResponseDto insert(ResponseDto responseDto);

    ResponseDto update(long id, ResponseDto responseDto);

    boolean delete(Long id);

    List<ResponseDto> getAllResponsesByExecutor();

    Page<ResponseDto> getAllResponsesByTaskId(Long taskId, int page, int size);

    ResponseDto changeResponseStatus(Long responseId, ResponseStatusDto responseStatusDto);
}
