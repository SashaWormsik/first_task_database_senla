package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> getAll();

    Optional<Category> getById(Category category);

    Optional<Category> getByName(Category category);

    Optional<List<Category>> getAllCategoryForTask(Task task);

    Category insert(Category category);

    boolean insertInTaskCategory(List<Category> categories, Task task);

    boolean deleteInTaskCategory(Task task);

    Category update(Category newCategory, Category oldCategory);

    boolean isCategoryPresent(Category category);

    boolean delete(Category category);
}
