package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;

public class EntityMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper = new ModelMapper();


    public String fromDtoToJson(Object object) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T fromJsonToDto(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T fromEntityToDto(Object entity, Class<T> classDto) {
        if (entity == null) return null;
        return modelMapper.map(entity, classDto);
    }

    public <T> T fromDtoToEntity(Object objectDto, Class<T> classEntity) {
        return modelMapper.map(objectDto, classEntity);
    }
}
