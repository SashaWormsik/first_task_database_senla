package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestException;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.charviakouski.freelanceExchange.service.ResponseStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResponseStatusServiceImpl implements ResponseStatusService {

    private final ResponseStatusRepository responseStatusRepository;
    private final EntityMapper entityMapper;

    @Override
    public ResponseStatusDto insert(ResponseStatusDto responseStatusDto) {
        log.info("Insert new ResponseStatus with status {}", responseStatusDto.getStatus());
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.save(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto update(long id, ResponseStatusDto responseStatusDto) {
        log.info("Update ResponseStatus with ID {}", id);
        if (!responseStatusRepository.existsById(id)) {
            log.info("ResponseStatus with ID {} does not exist", id);
            throw new MyBadRequestException("ResponseStatus with ID " + id + " does not exist");
        }
        ResponseStatus responseStatus = entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class);
        return entityMapper.fromEntityToDto(responseStatusRepository.save(responseStatus), ResponseStatusDto.class);
    }

    @Override
    public ResponseStatusDto getById(Long id) {
        log.info("Get ResponseStatus with ID {}", id);
        ResponseStatus responseStatus = responseStatusRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("ResponseStatus with ID {} not found", id);
                    return new MyBadRequestException("ResponseStatus not found");
                });
        return entityMapper.fromEntityToDto(responseStatus, ResponseStatusDto.class);
    }

    @Override
    public List<ResponseStatusDto> getAll() {
        log.info("Get ALL ResponseStatus");
        return responseStatusRepository.findAll().stream()
                .map(responseStatus -> entityMapper.fromEntityToDto(responseStatus, ResponseStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete responseStatus with ID {}", id);
        responseStatusRepository.deleteById(id);
        return responseStatusRepository.existsById(id);
    }
}
