package org.charviakouski.freelanceExchange.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class ResponseStatusServiceImpl implements ResponseStatusService {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private ResponseStatusRepository responseStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ResponseStatusDto insert(ResponseStatusDto responseStatusDto) {
        LOGGER.log(Level.INFO, "insert new ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.create(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto update(ResponseStatusDto responseStatusDto) {
        LOGGER.log(Level.INFO, "update ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.update(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto getById(ResponseStatusDto responseStatusDto) {
        Optional<ResponseStatus> optionalResponseStatus = responseStatusRepository.getById(responseStatusDto.getId());
        if (optionalResponseStatus.isEmpty()) {
            LOGGER.log(Level.INFO, "responseStatus with ID {} not found", responseStatusDto.getId());
            throw new ServiceException("ResponseStatus not found");
        }
        return entityMapper.fromEntityToDto(optionalResponseStatus.get(), ResponseStatusDto.class);
    }

    @Override
    public List<ResponseStatusDto> getAll() {
        LOGGER.log(Level.INFO, "get ALL responseStatus");
        return responseStatusRepository.getAll().stream()
                .map(responseStatus -> entityMapper.fromEntityToDto(responseStatus, ResponseStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(ResponseStatusDto responseStatusDto) {
        LOGGER.log(Level.INFO, "delete responseStatus with status {}", responseStatusDto.getStatus());
        responseStatusRepository.delete(responseStatusDto.getId());
        return responseStatusRepository.getById(responseStatusDto.getId()).isEmpty();
    }
}
