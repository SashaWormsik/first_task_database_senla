package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepository responseRepository;
    private final EntityMapper entityMapper;

    @Override
    public ResponseDto insert(ResponseDto responseDto) {
        log.info("insert new Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.save(response), ResponseDto.class);
    }

    @Override
    public ResponseDto update(ResponseDto responseDto) {
        log.info("update Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.save(response), ResponseDto.class);
    }

    @Override
    public ResponseDto getById(Long id) {
        Optional<Response> optionalResponse = responseRepository.findById(id);
        if (optionalResponse.isEmpty()) {
            log.info("response with ID = {} not found", id);
            throw new ServiceException("Response not found");
        }
        return entityMapper.fromEntityToDto(optionalResponse.get(), ResponseDto.class);
    }

    @Override
    public Page<ResponseDto> getAll(int page, int size, String sort) {
        log.info("get ALL response");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return responseRepository
                .findAll(pageable)
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete response with ID {}", id);
        responseRepository.deleteById(id);
        return !responseRepository.existsById(id);
    }

    @Override
    public List<ResponseDto> getAllResponsesByExecutor(Long id) {
        log.info("get ALL responses for Executor ID {}", id);
        return responseRepository.findAllResponsesByExecutor_Id(id).stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }
}
