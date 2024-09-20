package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseStatusController {
    @Autowired
    private ResponseStatusService responseStatusService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        return entityMapper.fromDtoToJson(responseStatusService.getAll());
    }

    public String getById(String jsonResponseStatusId) {
        ResponseStatusDto responseStatusDto = responseStatusService.getById(entityMapper.fromJsonToDto(jsonResponseStatusId, ResponseStatusDto.class));
        return entityMapper.fromDtoToJson(responseStatusDto);
    }

    public String insert(String jsonResponseStatus) {
        ResponseStatusDto responseStatusDto = responseStatusService.insert(entityMapper.fromJsonToDto(jsonResponseStatus, ResponseStatusDto.class));
        return entityMapper.fromDtoToJson(responseStatusDto);
    }

    public String update(String jsonResponseStatus) {
        ResponseStatusDto responseStatusDto = responseStatusService.update(entityMapper.fromJsonToDto(jsonResponseStatus, ResponseStatusDto.class));
        return entityMapper.fromDtoToJson(responseStatusDto);
    }

    public boolean delete(String jsonResponseStatus) {
        return responseStatusService.delete(entityMapper.fromJsonToDto(jsonResponseStatus, ResponseStatusDto.class));
    }
}
