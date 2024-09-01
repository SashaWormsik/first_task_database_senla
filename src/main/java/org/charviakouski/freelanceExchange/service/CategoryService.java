package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();

    CategoryDto getById(CategoryDto categoryDto) throws ServiceException;

    CategoryDto insert(CategoryDto categoryDto) throws ServiceException;

    CategoryDto update(CategoryDto categoryDto) throws ServiceException;

    boolean delete(CategoryDto categoryDto);
}
