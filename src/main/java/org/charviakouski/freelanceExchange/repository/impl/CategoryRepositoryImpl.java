package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class CategoryRepositoryImpl extends AbstractRepository<Long, Category> implements CategoryRepository {

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
