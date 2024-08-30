package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.ResponseStatusDto;
import org.charviakouski.freelanceExchange.model.entity.ResponseStatus;

@Data
public class ResponseStatusMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseStatusDto fromEntityToDto(ResponseStatus responseStatus) {
        ResponseStatusDto responseStatusDto = new ResponseStatusDto();
        responseStatusDto.setId(responseStatus.getId());
        responseStatusDto.setStatus(responseStatus.getStatus());
        return responseStatusDto;
    }

    public ResponseStatus fromDtoToEntity(ResponseStatusDto responseStatusDto) {
        ResponseStatus responseStatus = new ResponseStatus();
        responseStatus.setId(responseStatusDto.getId());
        responseStatus.setStatus(responseStatusDto.getStatus());
        return responseStatus;
    }

    @SneakyThrows
    public String fromDtoToJson(ResponseStatusDto responseStatusDto) {
        return objectMapper.writeValueAsString(responseStatusDto);
    }

    @SneakyThrows
    public ResponseStatusDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, ResponseStatusDto.class);
    }
}
