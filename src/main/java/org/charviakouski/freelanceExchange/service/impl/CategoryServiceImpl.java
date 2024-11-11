package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
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

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final EntityMapper entityMapper;


    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        log.info("insert new Category with name {}", categoryDto.getName());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        log.info("update UserInfo with ID {}", categoryDto.getId());
        Category category = entityMapper.fromDtoToEntity(categoryDto, Category.class);
        return entityMapper.fromEntityToDto(categoryRepository.save(category), CategoryDto.class);
    }

    @Override
    public CategoryDto getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            log.info("category with ID {} not found", id);
            throw new ServiceException("Category not found");
        }
        return entityMapper.fromEntityToDto(optionalCategory.get(), CategoryDto.class);
    }

    @Override
    public Page<CategoryDto> getAll(int page, int size, String sort) {
        log.info("get ALL category");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return categoryRepository
                .findAll(pageable)
                .map(category -> entityMapper.fromEntityToDto(category, CategoryDto.class));
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete category with ID {}", id);
        categoryRepository.deleteById(id);
        return !categoryRepository.existsById(id);
    }
}
