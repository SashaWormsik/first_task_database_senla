package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityMapper entityMapper;


    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.getAll().stream()
                .map(category -> entityMapper.fromEntityToDto(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(CategoryDto categoryDto) throws ServiceException {
        return null;

    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) throws ServiceException {
        return null;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) throws ServiceException {
        return null;
    }

    @Override
    public boolean delete(CategoryDto categoryDto) {
        return false;
    }
}
