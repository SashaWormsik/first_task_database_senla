package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getAll();

    Category getById(Category category);

    boolean insert(Category category);

    boolean update(Category category);

    boolean delete(Category category);
}
