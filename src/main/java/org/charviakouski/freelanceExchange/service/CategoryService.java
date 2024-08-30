package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    CategoryDto getById(CategoryDto categoryDto);

    boolean insert(CategoryDto categoryDto);

    boolean update(CategoryDto categoryDto);

    boolean delete(CategoryDto categoryDto);
}
