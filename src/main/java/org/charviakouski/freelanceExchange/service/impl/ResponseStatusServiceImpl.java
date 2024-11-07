package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResponseStatusServiceImpl implements ResponseStatusService {

    private final ResponseStatusRepository responseStatusRepository;
    private final EntityMapper entityMapper;

    @Override
    public ResponseStatusDto insert(ResponseStatusDto responseStatusDto) {
        log.info("insert new ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.save(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto update(ResponseStatusDto responseStatusDto) {
        log.info("update ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.save(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto getById(Long id) {
        Optional<ResponseStatus> optionalResponseStatus = responseStatusRepository.findById(id);
        if (optionalResponseStatus.isEmpty()) {
            log.info("responseStatus with ID {} not found", id);
            throw new ServiceException("ResponseStatus not found");
        }
        return entityMapper.fromEntityToDto(optionalResponseStatus.get(), ResponseStatusDto.class);
    }

    @Override
    public List<ResponseStatusDto> getAll() {
        log.info("get ALL responseStatus");
        return responseStatusRepository.findAll().stream()
                .map(responseStatus -> entityMapper.fromEntityToDto(responseStatus, ResponseStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete responseStatus with ID {}", id);
        responseStatusRepository.deleteById(id);
        return responseStatusRepository.existsById(id);
    }
}
