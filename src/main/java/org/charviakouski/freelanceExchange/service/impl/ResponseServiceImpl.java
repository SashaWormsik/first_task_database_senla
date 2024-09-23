package org.charviakouski.freelanceExchange.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

@Component
@Transactional
public class ResponseServiceImpl implements ResponseService {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ResponseDto insert(ResponseDto responseDto) {
        LOGGER.log(Level.INFO, "insert new Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.create(response), ResponseDto.class);
    }

    @Override
    public ResponseDto update(ResponseDto responseDto) {
        LOGGER.log(Level.INFO, "update Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.update(response), ResponseDto.class);
    }

    @Override
    public ResponseDto getById(ResponseDto responseDto) {
        Optional<Response> optionalResponse = responseRepository.getById(responseDto.getId());
        if (optionalResponse.isEmpty()) {
            LOGGER.log(Level.INFO, "response with ID = {} not found", responseDto.getTask().getId());
            throw new ServiceException("Response not found");
        }
        return entityMapper.fromEntityToDto(optionalResponse.get(), ResponseDto.class);
    }

    @Override
    public List<ResponseDto> getAll() {
        LOGGER.log(Level.INFO, "get ALL response");
        return responseRepository.getAll().stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }

    @Override
    public boolean delete(ResponseDto responseDto) {
        LOGGER.log(Level.INFO, "delete response with ID {}", responseDto.getId());
        responseRepository.delete(responseDto.getId());
        return responseRepository.getById(responseDto.getId()).isEmpty();
    }

    @Override
    public List<ResponseDto> getAllResponsesByExecutor(UserInfoDto userInfodto) {
        LOGGER.log(Level.INFO, "get ALL responses for Executor {}", userInfodto.getName());
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfodto, UserInfo.class);
        return responseRepository.getAllResponsesByExecutor(userInfo).stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }
}
