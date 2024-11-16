package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestExseption;
import org.charviakouski.freelanceExchange.model.dto.CategoryDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EntityMapper entityMapper;


    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        log.info("Insert new Category with name {}", categoryDto.getName());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto update(Long id, CategoryDto categoryDto) {
        log.info("Update Category with ID {}", categoryDto.getId());
        if (!categoryRepository.existsById(id)) {
            log.info("Category with ID {} does not exist", id);
            throw new MyBadRequestExseption("Category with ID " + id + " does not exist");
        }
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto getById(Long id) {
        log.info("Get Category with ID {}", id);
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("Category with ID {} not found", id);
                    return new MyBadRequestExseption("Category with ID " + id + " not found");
                });
        return entityMapper.fromEntityToDto(category, CategoryDto.class);
    }

    @Override
    public Page<CategoryDto> getAll(int page, int size, String sort) {
        log.info("Get ALL category");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return categoryRepository
                .findAll(pageable)
                .map(category -> entityMapper.fromEntityToDto(category, CategoryDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("Delete category with ID {}", id);
        categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }
}
