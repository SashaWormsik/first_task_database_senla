package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {
    private final List<Category> categories = new ArrayList<>();

    {
        categories.add(new Category(1, "IT"));
        categories.add(new Category(2, "UX/UI design"));
        categories.add(new Category(3, "construction"));
        categories.add(new Category(4, "architecture"));
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getById(Category category) {
        return categories.stream().filter(cat -> cat.getId().equals(category.getId())).findAny().orElse(null);
    }

    @Override
    public boolean insert(Category category) {
        return categories.add(category);
    }

    @Override
    public boolean update(Category category) {
        categories.removeIf(category1 -> category1.getId().equals(category.getId()));
        return categories.add(category);
    }

    @Override
    public boolean delete(Category category) {
        return categories.removeIf(category1 -> category1.getId().equals(category.getId()));

    }
}
