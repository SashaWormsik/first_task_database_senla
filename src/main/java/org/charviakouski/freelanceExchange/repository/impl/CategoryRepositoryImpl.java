package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public List<Category> getAll() {
        return null;
    }

    @Override
    public Optional<Category> getById(Category category) {
        return null;
    }

    @Override
    public Category insert(Category category) {
        return null;
    }

    @Override
    public Category update(Category newCategory, Category oldCategory) {
        return null;
    }

    @Override
    public boolean delete(Category category) {
        return false;

    }
}
