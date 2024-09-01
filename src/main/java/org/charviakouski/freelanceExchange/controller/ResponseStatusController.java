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
        responseStatusService.getAll();
        return null;
    }

    public String getById(String jsonResponseStatusId) {
        responseStatusService.getById(entityMapper.fromJsonToDto(jsonResponseStatusId, ResponseStatusDto.class));
        return null;
    }

    public String insert(String jsonResponseStatus) {
        responseStatusService.insert(entityMapper.fromJsonToDto(jsonResponseStatus, ResponseStatusDto.class));
        return null;
    }

    public String update(String jsonResponseStatus) {
        responseStatusService.update(entityMapper.fromJsonToDto(jsonResponseStatus, ResponseStatusDto.class));
        return null;
    }

    public boolean delete(String jsonResponseStatus) {
        responseStatusService.delete(entityMapper.fromJsonToDto(jsonResponseStatus, ResponseStatusDto.class));
        return false;
    }
}
