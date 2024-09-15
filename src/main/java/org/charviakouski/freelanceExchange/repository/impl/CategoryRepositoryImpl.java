package org.charviakouski.freelanceExchange.repository.impl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
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

    private static final String SELECT_CATEGORY_BY_NAME = "SELECT id AS category_id, name AS category_name " + "FROM category WHERE name = ?";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT id AS category_id, name AS category_name " + "FROM category WHERE id = ?::integer";
    private static final String SELECT_ALL_CATEGORY_FOR_TASK = "SELECT id AS category_id, name AS category_name " + "FROM category " + "JOIN task_category ON category.id = task_category.category_id " + "WHERE task_category.task_id = ?;";
    private static final String CATEGORY_IS_PRESENT = "SELECT EXISTS(SELECT * FROM category WHERE category.name = ?);";
    private static final String INSERT_NEW_CATEGORY = "INSERT INTO category (name) " + "VALUES (?);";
    private static final String INSERT_CATEGORY_FOR_TASK_IN_TASK_CATEGORY_TABLE = "INSERT INTO task_category (category_id, task_id) " + "VALUES (? , ?)";
    private static final String UPDATE_CATEGORY = "UPDATE category SET name = ? WHERE id = ?;";
    private static final String DELETE_CATEGORY_FOR_TASK_IN_TASK_CATEGORY_TABLE = "DELETE FROM task_category WHERE task_id = ?;";

    @Autowired
    private ConnectionHolder connectionHolder;
    @Autowired
    private MapperFromResultSetToEntity<Category> categoryMapper;

    @Override
    public Optional<Category> getById(Category category) {
        return getCategoryByCriteria(category.getId().toString(), SELECT_CATEGORY_BY_ID);
    }

    @Override
    public Optional<Category> getByName(Category category) {
        return getCategoryByCriteria(category.getName(), SELECT_CATEGORY_BY_NAME);
    }

    @SneakyThrows
    @Override
    public Optional<List<Category>> getAllCategoryForTask(Task task) {
        List<Category> categories = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY_FOR_TASK)) {
            statement.setLong(1, task.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    categoryMapper.map(resultSet).ifPresent(categories::add);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionHolder.releaseConnection();
        }
        return categories.isEmpty() ? Optional.empty() : Optional.of(categories);
    }

    @SneakyThrows
    @Override
    public Category insert(Category category) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            int row = statement.executeUpdate();
            if (row == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    resultSet.next();
                    category.setId(resultSet.getLong(1));
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return category;
    }

    @SneakyThrows
    @Override
    public boolean insertInTaskCategory(List<Category> categories, Task task) {
        int[] result;
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY_FOR_TASK_IN_TASK_CATEGORY_TABLE)) {
            for (Category category : categories) {
                statement.setLong(1, category.getId());
                statement.setLong(2, task.getId());
                statement.addBatch();
            }
            result = statement.executeBatch();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return result != null && result.length > 0;
    }

    @SneakyThrows
    @Override
    public boolean deleteInTaskCategory(Task task) {
        int result;
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_FOR_TASK_IN_TASK_CATEGORY_TABLE)) {
            statement.setLong(1, task.getId());
            result = statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return result > 0;
    }

    @SneakyThrows
    @Override
    public Category update(Category newCategory, Category oldCategory) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY)) {
            statement.setString(1, newCategory.getName());
            statement.setLong(2, oldCategory.getId());
            int row = statement.executeUpdate();
            if (row == 1) {
                newCategory.setId(oldCategory.getId());
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return newCategory;
    }

    @SneakyThrows
    @Override
    public boolean isCategoryPresent(Category category) {
        boolean result = false;
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(CATEGORY_IS_PRESENT)) {
            statement.setString(1, category.getName());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    result = resultSet.getBoolean(1);
                }
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
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

    @SneakyThrows
    private Optional<Category> getCategoryByCriteria(String criteria, String queryString) {
        Optional<Category> optionalCategory = Optional.empty();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(queryString)) {
            statement.setObject(1, criteria);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalCategory = categoryMapper.map(resultSet);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return optionalCategory;
    }

}
