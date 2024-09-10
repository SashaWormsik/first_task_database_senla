package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String SELECT_CATEGORY_BY_NAME =
            "SELECT id AS category_id, name AS category_name " +
                    "FROM category WHERE name = ?";
    private static final String SELECT_CATEGORY_BY_ID =
            "SELECT id AS category_id, name AS category_name " +
                    "FROM category WHERE id = ?::integer";
    private static final String SELECT_ALL_CATEGORY_FOR_TASK =
            "SELECT id AS category_id, name AS category_name " +
                    "FROM category " +
                    "JOIN task_category ON category.id = task_category.category_id " +
                    "WHERE task_category.task_id = ?;";
    private static final String CATEGORY_IS_PRESENT =
            "SELECT EXISTS(SELECT * FROM category WHERE category.name = ?);";
    private static final String INSERT_NEW_CATEGORY =
            "INSERT INTO category (name) " +
                    "VALUES (?);";
    private static final String INSERT_CATEGORY_FOR_TASK_IN_TASK_CATEGORY_TABLE =
            "INSERT INTO task_category (category_id, task_id) " +
                    "VALUES (? , ?)";
    private static final String UPDATE_CATEGORY =
            "UPDATE category SET name = ? WHERE id = ?;";

    @Autowired
    private ConnectionHolder connectionHolder;
    @Autowired
    private MapperFromResultSetToEntity<Category> categoryMapper;

    @Override
    public Optional<Category> getById(Category category) {
        return getCategoryByCriteria(category.getId().toString(), SELECT_CATEGORY_BY_ID);
    }

    public Optional<Category> getByName(Category category) {
        return getCategoryByCriteria(category.getName(), SELECT_CATEGORY_BY_NAME);
    }

    public List<Category> getAllCategoryForTask(Task task) {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY_FOR_TASK)) {
            statement.setLong(1, task.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    categoryMapper.map(resultSet).ifPresent(categories::add);
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return categories;
    }

    @Override
    public Category insert(Category category) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            int row = statement.executeUpdate();
            if (row == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    category.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException exception) {
            throw new RepositoryException(exception);
        }
        return category;
    }

    public boolean insertInTaskCategory(List<Category> categories, Task task) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY_FOR_TASK_IN_TASK_CATEGORY_TABLE)) {
            connection.setAutoCommit(false);
            for (Category category : categories) {
                statement.setLong(1, category.getId());
                statement.setLong(2, task.getId());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new RepositoryException(exception);
        }
        return true;
    }

    @Override
    public Category update(Category newCategory, Category oldCategory) {
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
            statement.setString(1, newCategory.getName());
            statement.setLong(2, oldCategory.getId());
            int row = statement.executeUpdate();
            if (row == 1) {
                newCategory.setId(oldCategory.getId());
            }
        } catch (SQLException exception) {
            throw new RepositoryException(exception);
        }
        return newCategory;
    }

    public boolean categoryIsPresent(Category category) {
        boolean result = false;
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(CATEGORY_IS_PRESENT)) {
            statement.setString(1, category.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getBoolean(1);
                }
            }
        } catch (SQLException exception) {
            throw new RepositoryException(exception);
        }
        return result;
    }

    @Override
    public boolean delete(Category category) {
        return false;
    }

    @Override
    public List<Category> getAll() {
        return null;
    }

    private Optional<Category> getCategoryByCriteria(String criteria, String queryString) {
        Optional<Category> optionalCategory = Optional.empty();
        try (Connection connection = connectionHolder.getConnection();
             PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setObject(1, criteria);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalCategory = categoryMapper.map(resultSet);
            }
        } catch (SQLException exception) {
            throw new RepositoryException(exception);
        }
        return optionalCategory;
    }

}
