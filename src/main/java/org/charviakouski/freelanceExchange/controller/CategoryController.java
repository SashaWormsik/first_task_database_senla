package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        return entityMapper.fromDtoToJson(categoryService.getAll());
    }

    public String getById(String jsonCategoryId) {
        CategoryDto categoryDto = categoryService.getById(entityMapper.fromJsonToDto(jsonCategoryId, CategoryDto.class));
        return entityMapper.fromDtoToJson(categoryDto);
    }

    public String insert(String jsonCategory) {
        CategoryDto categoryDto = categoryService.insert(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
        return entityMapper.fromDtoToJson(categoryDto);
    }

    public String update(String jsonCategory) {
        CategoryDto categoryDto = categoryService.update(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
        return entityMapper.fromDtoToJson(categoryDto);
    }

    public boolean delete(String jsonCategory) {
        return categoryService.delete(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
    }
}
