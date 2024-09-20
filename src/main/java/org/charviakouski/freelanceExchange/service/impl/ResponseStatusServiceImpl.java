package org.charviakouski.freelanceExchange.service.impl;

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
    @Autowired
    private ResponseStatusRepository responseStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ResponseStatusDto insert(ResponseStatusDto responseStatusDto) {
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.create(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto update(ResponseStatusDto responseStatusDto) {
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.update(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto getById(ResponseStatusDto responseStatusDto) {
        Optional<ResponseStatus> optionalResponseStatus = responseStatusRepository.getById(responseStatusDto.getId());
        if (optionalResponseStatus.isEmpty()) {
            throw new ServiceException("ResponseStatus not found");
        }
        return entityMapper.fromEntityToDto(optionalResponseStatus.get(), ResponseStatusDto.class);
    }

    @Override
    public List<ResponseStatusDto> getAll() {
        return responseStatusRepository.getAll().stream()
                .map(responseStatus -> entityMapper.fromEntityToDto(responseStatus, ResponseStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(ResponseStatusDto responseStatusDto) {
        responseStatusRepository.delete(responseStatusDto.getId());
        return responseStatusRepository.getById(responseStatusDto.getId()).isEmpty();
    }
}
