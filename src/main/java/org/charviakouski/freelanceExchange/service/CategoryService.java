package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.springframework.data.domain.Page;

public interface CategoryService {
    Page<CategoryDto> getAll(int page, int size, String sort);

    CategoryDto getById(Long id);

    CategoryDto insert(CategoryDto categoryDto);

    CategoryDto update(CategoryDto categoryDto);

    boolean delete(Long id);
}
