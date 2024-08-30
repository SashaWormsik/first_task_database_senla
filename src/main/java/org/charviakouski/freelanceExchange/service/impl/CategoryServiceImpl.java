package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
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
    EntityMapper entityMapper;


    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.getAll().stream()
                .map(category -> entityMapper.fromEntityToDto(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getById(CategoryDto categoryDto) {
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.getById(category), CategoryDto.class);

    }

    @Override
    public boolean insert(CategoryDto categoryDto) {
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return categoryRepository.insert(category);
    }

    @Override
    public boolean update(CategoryDto categoryDto) {
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return categoryRepository.update(category);
    }

    @Override
    public boolean delete(CategoryDto categoryDto) {
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return categoryRepository.delete(category);
    }
}
