package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;

import java.util.List;

public interface ResponseService {
    List<ResponseDto> getAll();

    ResponseDto getById(ResponseDto responseDto);

    ResponseDto insert(ResponseDto responseDto);

    ResponseDto update(ResponseDto responseDto);

    boolean delete(ResponseDto responseDto);

    List<ResponseDto> getAllResponsesByExecutor(UserInfoDto userInfodto);
}
