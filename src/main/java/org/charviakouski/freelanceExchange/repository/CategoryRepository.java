package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAll();

    Optional<Category> getById(Category category);

    Category insert(Category category);

    Category update(Category newCategory, Category oldCategory);

    boolean delete(Category category);
}
