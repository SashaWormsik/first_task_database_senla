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
        return entityMapper.fromDtoToJson(categoryService.getById(entityMapper.fromJsonToDto(jsonCategoryId, CategoryDto.class)));
    }


    public boolean insert(String jsonCategory) {
        return categoryService.insert(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
    }

    public boolean update(String jsonCategory) {
        return categoryService.update(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
    }

    public boolean delete(String jsonCategory) {
        return categoryService.delete(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
    }
}
