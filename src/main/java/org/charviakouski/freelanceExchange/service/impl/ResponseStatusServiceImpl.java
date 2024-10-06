package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional
public class ResponseStatusServiceImpl implements ResponseStatusService {

    @Autowired
    private ResponseStatusRepository responseStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ResponseStatusDto insert(ResponseStatusDto responseStatusDto) {
        log.info("insert new ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.create(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto update(ResponseStatusDto responseStatusDto) {
        log.info("update ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.update(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto getById(Long id) {
        Optional<ResponseStatus> optionalResponseStatus = responseStatusRepository.getById(id);
        if (optionalResponseStatus.isEmpty()) {
            log.info("responseStatus with ID {} not found", id);
            throw new ServiceException("ResponseStatus not found");
        }
        return entityMapper.fromEntityToDto(optionalResponseStatus.get(), ResponseStatusDto.class);
    }

    @Override
    public List<ResponseStatusDto> getAll() {
        log.info("get ALL responseStatus");
        return responseStatusRepository.getAll().stream()
                .map(responseStatus -> entityMapper.fromEntityToDto(responseStatus, ResponseStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete responseStatus with ID {}", id);
        return responseStatusRepository.delete(id);
    }
}
