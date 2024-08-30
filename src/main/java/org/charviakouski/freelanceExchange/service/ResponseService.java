package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;

import java.util.List;

public interface ResponseService {
    List<ResponseDto> getAll();

    ResponseDto getById(ResponseDto responseDto);

    boolean insert(ResponseDto responseDto);

    boolean update(ResponseDto responseDto);

    boolean delete(ResponseDto responseDto);
}
