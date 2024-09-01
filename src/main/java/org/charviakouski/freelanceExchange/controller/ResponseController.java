package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseController {
    @Autowired
    private ResponseService responseService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        responseService.getAll();
        return null;
    }

    public String getById(String jsonResponseId) {
        responseService.getById(entityMapper.fromJsonToDto(jsonResponseId, ResponseDto.class));
        return null;
    }

    public String insert(String jsonResponse) {
        responseService.insert(entityMapper.fromJsonToDto(jsonResponse, ResponseDto.class));
        return null;
    }

    public String update(String jsonResponse) {
        responseService.update(entityMapper.fromJsonToDto(jsonResponse, ResponseDto.class));
        return null;
    }

    public boolean delete(String jsonResponse) {
        responseService.delete(entityMapper.fromJsonToDto(jsonResponse, ResponseDto.class));
        return false;
    }
}
