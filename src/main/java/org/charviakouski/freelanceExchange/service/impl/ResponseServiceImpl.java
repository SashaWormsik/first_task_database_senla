package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseServiceImpl implements ResponseService {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<ResponseDto> getAll() {
        return null;
    }

    @Override
    public ResponseDto getById(ResponseDto responseDto) {
        return null;
    }

    @Override
    public ResponseDto insert(ResponseDto responseDto) {
        return null;
    }

    @Override
    public ResponseDto update(ResponseDto responseDto) {
        return null;
    }

    @Override
    public boolean delete(ResponseDto responseDto) {
        return false;
    }
}
