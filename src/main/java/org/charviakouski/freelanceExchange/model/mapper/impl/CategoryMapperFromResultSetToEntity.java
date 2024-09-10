package org.charviakouski.freelanceExchange.model.mapper.impl;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class CategoryMapperFromResultSetToEntity implements MapperFromResultSetToEntity<Category> {
    @Override
    public Optional<Category> map(ResultSet resultSet) {
        Category category = new Category();
        Optional<Category> optionalCategory;
        try {
            category.setId(resultSet.getLong("category_id"));
            category.setName(resultSet.getString("category_name"));
            optionalCategory = Optional.of(category);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return optionalCategory;
    }
}
