package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ResponseDto insert(ResponseDto responseDto) {
        log.info("insert new Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.create(response), ResponseDto.class);
    }

    @Override
    public ResponseDto update(ResponseDto responseDto) {
        log.info("update Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.update(response), ResponseDto.class);
    }

    @Override
    public ResponseDto getById(Long id) {
        Optional<Response> optionalResponse = responseRepository.getById(id);
        if (optionalResponse.isEmpty()) {
            log.info("response with ID = {} not found", id);
            throw new ServiceException("Response not found");
        }
        return entityMapper.fromEntityToDto(optionalResponse.get(), ResponseDto.class);
    }

    @Override
    public List<ResponseDto> getAll() {
        log.info("get ALL response");
        return responseRepository.getAll().stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete response with ID {}", id);
        responseRepository.delete(id);
        return responseRepository.getById(id).isEmpty();
    }

    @Override
    public List<ResponseDto> getAllResponsesByExecutor(Long id) {
        log.info("get ALL responses for Executor ID {}", id);
        UserInfo userInfo = UserInfo.builder().id(id).build();
        return responseRepository.getAllResponsesByExecutor(userInfo).stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }
}
