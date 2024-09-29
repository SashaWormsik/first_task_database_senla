package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseController {
    @Autowired
    private ResponseService responseService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        return entityMapper.fromDtoToJson(responseService.getAll());
    }

    public String getById(String jsonResponseId) {
        ResponseDto responseDto = responseService.getById(entityMapper.fromJsonToDto(jsonResponseId, ResponseDto.class));
        return entityMapper.fromDtoToJson(responseDto);
    }

    public String insert(String jsonResponse) {
        ResponseDto responseDto = responseService.insert(entityMapper.fromJsonToDto(jsonResponse, ResponseDto.class));
        return entityMapper.fromDtoToJson(responseDto);
    }

    public String update(String jsonResponse) {
        ResponseDto responseDto = responseService.update(entityMapper.fromJsonToDto(jsonResponse, ResponseDto.class));
        return entityMapper.fromDtoToJson(responseDto);
    }

    public boolean delete(String jsonResponse) {
        return responseService.delete(entityMapper.fromJsonToDto(jsonResponse, ResponseDto.class));
    }

    public String getAllResponsesByExecutor(String jsonUserInfo) {
        List<ResponseDto> responseDto = responseService.getAllResponsesByExecutor(entityMapper.fromJsonToDto(jsonUserInfo, UserInfoDto.class));
        return entityMapper.fromDtoToJson(responseDto);

    }
}
