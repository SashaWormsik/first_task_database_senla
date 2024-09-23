package org.charviakouski.freelanceExchange.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityMapper entityMapper;


    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        LOGGER.log(Level.INFO, "insert new Category with name {}", categoryDto.getName());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.create(category), CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        LOGGER.log(Level.INFO, "update UserInfo with ID {}", categoryDto.getId());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.update(category), CategoryDto.class);
    }

    @Override
    public CategoryDto getById(CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.getById(categoryDto.getId());
        if (optionalCategory.isEmpty()) {
            LOGGER.log(Level.INFO, "category with ID {} not found", categoryDto.getId());
            throw new ServiceException("Category not found");
        }
        return entityMapper.fromEntityToDto(optionalCategory.get(), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        LOGGER.log(Level.INFO, "get ALL category");
        return categoryRepository.getAll().stream()
                .map(category -> entityMapper.fromEntityToDto(category, CategoryDto.class))
                .toList();
    }

    @Override
    public boolean delete(CategoryDto categoryDto) {
        LOGGER.log(Level.INFO, "delete category with name {}", categoryDto.getName());
        categoryRepository.delete(categoryDto.getId());
        return categoryRepository.getById(categoryDto.getId()).isEmpty();
    }
}
