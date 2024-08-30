package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.entity.Category;

@Data
public class CategoryMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CategoryDto fromEntityToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }

    public Category fromDtoToEntity(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    @SneakyThrows
    public String fromDtoToJson(CategoryDto categoryDto) {
        return objectMapper.writeValueAsString(categoryDto);
    }

    @SneakyThrows
    public CategoryDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, CategoryDto.class);
    }
}
