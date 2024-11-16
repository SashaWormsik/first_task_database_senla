package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestExseption;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.entity.Response;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.ResponseRepository;
import org.charviakouski.freelanceExchange.repository.ResponseStatusRepository;
import org.charviakouski.freelanceExchange.service.ResponseService;
import org.charviakouski.freelanceExchange.util.PrincipalUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final String RESPONSE_STATUS = "WAITING";

    private final ResponseRepository responseRepository;
    private final ResponseStatusRepository responseStatusRepository;
    private final EntityMapper entityMapper;
    private final PrincipalUtil principalUtil;

    @Override
    public ResponseDto insert(ResponseDto responseDto) {
        log.info("insert new Response with Task ID = {}", responseDto.getTask().getId());
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        response.setCreateDate(new Date());
        response.setResponseStatus(responseStatusRepository.findByStatus(RESPONSE_STATUS));
        return entityMapper.fromEntityToDto(responseRepository.save(response), ResponseDto.class);
    }

    @Override
    public ResponseDto update(ResponseDto responseDto) {
        log.info("update Response with ID = {}", responseDto.getId());
        if (!principalUtil.checkId(responseDto.getExecutor().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.save(response), ResponseDto.class);
    }

    @Override
    public ResponseDto getById(Long id) {
        log.info("get response with ID = {}", id);
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
        Response response = responseRepository.findById(id)
                .orElseThrow(() -> new MyBadRequestExseption("Response not found with ID" + id));
        if (!principalUtil.checkId(response.getExecutor().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        responseRepository.deleteById(id);
        return !responseRepository.existsById(id);
    }

    @Override
    public List<ResponseDto> getAllResponsesByExecutor() {
        log.info("get ALL responses for Executor ");
        return responseRepository.findAllResponsesByExecutor_Id(principalUtil.getCurrentUserId()).stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }

    @Override
    public Page<ResponseDto> getAllResponsesByTaskId(Long taskId, int page, int size) {
        log.info("get ALL responses for Task ID {}", taskId);
        Pageable pageable = PageRequest.of(page - 1, size);
        return responseRepository.findAllResponsesByTask_Id(taskId, pageable)
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class));
    }

    @Override
    public ResponseDto changeResponseStatus(Long responseId, ResponseStatusDto responseStatusDto) {
        log.info("change Response Status with ID {} and Status {}", responseId, responseStatusDto.getStatus());
        Optional<Response> optionalResponse = responseRepository.findById(responseId);
        if (optionalResponse.isEmpty()) {
            throw new MyBadRequestExseption("Response not found with ID " + responseId);
        }
        Response response = optionalResponse.get();
        if (!principalUtil.checkId(response.getTask().getCustomer().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        response.setResponseStatus(entityMapper.fromDtoToEntity(responseStatusDto, ResponseStatus.class));
        return entityMapper.fromEntityToDto(responseRepository.save(response), ResponseDto.class);
    }
}
