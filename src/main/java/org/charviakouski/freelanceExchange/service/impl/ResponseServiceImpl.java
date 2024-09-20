package org.charviakouski.freelanceExchange.service.impl;

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
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public ResponseDto insert(ResponseDto responseDto) {
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.create(response), ResponseDto.class);
    }

    @Override
    public ResponseDto update(ResponseDto responseDto) {
        Response response = entityMapper.fromDtoToEntity(responseDto, Response.class);
        return entityMapper.fromEntityToDto(responseRepository.update(response), ResponseDto.class);
    }

    @Override
    public ResponseDto getById(ResponseDto responseDto) {
        Optional<Response> optionalResponse = responseRepository.getById(responseDto.getId());
        if (optionalResponse.isEmpty()) {
            throw new ServiceException("Response not found");
        }
        return entityMapper.fromEntityToDto(optionalResponse.get(), ResponseDto.class);
    }

    @Override
    public List<ResponseDto> getAll() {
        return responseRepository.getAll().stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }

    @Override
    public boolean delete(ResponseDto responseDto) {
        responseRepository.delete(responseDto.getId());
        return responseRepository.getById(responseDto.getId()).isEmpty();
    }

    @Override
    public List<ResponseDto> getAllResponsesByExecutor(UserInfoDto userInfodto) {
        UserInfo userInfo = entityMapper.fromDtoToEntity(userInfodto, UserInfo.class);
        return responseRepository.getAllResponsesByExecutor(userInfo).stream()
                .map(response -> entityMapper.fromEntityToDto(response, ResponseDto.class))
                .toList();
    }
}
