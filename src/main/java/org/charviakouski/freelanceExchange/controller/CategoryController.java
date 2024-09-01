package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.exception.ServiceException;
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
        categoryService.getAll();
        return null;
    }

    public String getById(String jsonCategoryId) throws ServiceException {
        categoryService.getById(entityMapper.fromJsonToDto(jsonCategoryId, CategoryDto.class));
        return null;
    }

    public String insert(String jsonCategory) throws ServiceException {
        categoryService.insert(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
        return null;
    }

    public String update(String jsonCategory) throws ServiceException {
        categoryService.update(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
        return null;
    }

    public boolean delete(String jsonCategory) {
        categoryService.delete(entityMapper.fromJsonToDto(jsonCategory, CategoryDto.class));
        return false;
    }
}
