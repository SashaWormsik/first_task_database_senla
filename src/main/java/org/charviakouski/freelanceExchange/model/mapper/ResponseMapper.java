package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.ResponseDto;
import org.charviakouski.freelanceExchange.model.entity.Response;

@Data
public class ResponseMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private TaskMapper taskMapper = new TaskMapper();
    private UserInfoMapper userInfoMapper = new UserInfoMapper();
    private ResponseStatusMapper responseStatusMapper = new ResponseStatusMapper();

    public ResponseDto fromEntityToDto(Response response) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(response.getId());
        responseDto.setSuggestedDate(response.getSuggestedDate());
        responseDto.setSuggestedPrice(response.getSuggestedPrice());
        responseDto.setCreateDate(response.getCreateDate());
        responseDto.setTaskId(taskMapper.fromEntityToDto(response.getTaskId()));
        responseDto.setExecutorId(userInfoMapper.fromEntityToDto(response.getExecutorId()));
        responseDto.setResponseStatusId(responseStatusMapper.fromEntityToDto(response.getResponseStatusId()));
        return responseDto;
    }

    public Response fromDtoToEntity(ResponseDto responseDto) {
        Response response = new Response();
        response.setId(responseDto.getId());
        response.setSuggestedPrice(responseDto.getSuggestedPrice());
        response.setSuggestedDate(responseDto.getSuggestedDate());
        response.setCreateDate(responseDto.getCreateDate());
        response.setTaskId(taskMapper.fromDtoToEntity(responseDto.getTaskId()));
        response.setExecutorId(userInfoMapper.fromDtoToEntity(responseDto.getExecutorId()));
        response.setResponseStatusId(responseStatusMapper.fromDtoToEntity(responseDto.getResponseStatusId()));
        return response;
    }

    @SneakyThrows
    public String fromDtoToJson(ResponseDto responseDto) {
        return objectMapper.writeValueAsString(responseDto);
    }

    @SneakyThrows
    public ResponseDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, ResponseDto.class);
    }
}
