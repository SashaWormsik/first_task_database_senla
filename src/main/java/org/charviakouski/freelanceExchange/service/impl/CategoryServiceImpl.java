package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Component
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityMapper entityMapper;


    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        log.info("insert new Category with name {}", categoryDto.getName());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.create(category), CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("update UserInfo with ID {}", categoryDto.getId());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.update(category), CategoryDto.class);
    }

    @Override
    public CategoryDto getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.getById(id);
        if (optionalCategory.isEmpty()) {
            log.info("category with ID {} not found", id);
            throw new ServiceException("Category not found");
        }
        return entityMapper.fromEntityToDto(optionalCategory.get(), CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAll() {
        log.info("get ALL category");
        return categoryRepository.getAll().stream()
                .map(category -> entityMapper.fromEntityToDto(category, CategoryDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete category with ID {}", id);
        categoryRepository.delete(id);
        return categoryRepository.getById(id).isEmpty();
    }
}
