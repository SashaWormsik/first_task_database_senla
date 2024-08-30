package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseStatusServiceImpl implements ResponseStatusService {
    @Autowired
    private ResponseStatusRepository responseStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<ResponseStatusDto> getAll() {
        return null;
    }

    @Override
    public ResponseStatusDto getById(ResponseStatusDto responseStatusDto) {
        return null;
    }

    @Override
    public boolean insert(ResponseStatusDto responseStatusDto) {
        return false;
    }

    @Override
    public boolean update(ResponseStatusDto responseStatusDto) {
        return false;
    }

    @Override
    public boolean delete(ResponseStatusDto responseStatusDto) {
        return false;
    }
}
