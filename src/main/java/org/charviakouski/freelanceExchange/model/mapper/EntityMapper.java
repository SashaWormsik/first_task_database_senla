package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.modelmapper.ModelMapper;

public class EntityMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper = new ModelMapper();


    @SneakyThrows
    public String fromDtoToJson(Object object) {
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    @SneakyThrows
    public <T> T fromJsonToDto(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }

    public <T> T fromEntityToDto(Object entity, Class<T> classDto) {
        if (entity == null) return null;
        return modelMapper.map(entity, classDto);
    }

    public <T> T fromDtoToEntity(Object objectDto, Class<T> classEntity) {
        return modelMapper.map(objectDto, classEntity);
    }
}
