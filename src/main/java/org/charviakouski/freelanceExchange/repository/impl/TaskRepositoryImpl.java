package org.charviakouski.freelanceExchange.repository.impl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskRepositoryImpl implements TaskRepository {
    private static final String SELECT_ALL = "SELECT task.id AS task_id, task.title AS task_title, task.description AS task_description, task.price AS task_price, " +
            "       task.deadline AS task_deadline, task.create_date AS task_create_date, " +
            "       user_info.id AS user_info_id, user_info.name AS user_info_name, user_info.surname AS user_info_surname, " +
            "       user_info.profession AS user_info_profession, user_info.work_experience AS user_info_work_experience, user_info.description AS user_info_description, " +
            "       task_status.id AS task_status_id, task_status.status AS task_status_status " +

            "FROM task " +
            "         JOIN user_info ON task.customer_id = user_info.id " +
            "         JOIN task_status ON task.status_id = task_status.id;";
    private static final String SELECT_TASK_BY_ID =
            "SELECT task.id AS task_id, task.title AS task_title, task.description AS task_description, task.price AS task_price, " +
                    "       task.deadline AS task_deadline, task.create_date AS task_create_date, " +
                    "       user_info.id AS user_info_id, user_info.name AS user_info_name, user_info.surname AS user_info_surname, " +
                    "       user_info.profession AS user_info_profession, user_info.work_experience AS user_info_work_experience, user_info.description AS user_info_description, " +
                    "       task_status.id AS task_status_id, task_status.status AS task_status_status " +

                    "FROM task " +
                    "         JOIN user_info ON task.customer_id = user_info.id " +
                    "         JOIN task_status ON task.status_id = task_status.id " +
                    "WHERE task.id = ?;";
    private static final String INSERT_NEW_TASK =
            "INSERT INTO task (title, description, price, deadline, create_date, customer_id, status_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, " +
                    "(SELECT id FROM task_status WHERE task_status.status = 'in waiting'));";
    private static final String UPDATE_TASK =
            "UPDATE task " +
                    "SET title = ?, " +
                    "description = ?, " +
                    "price = ?, " +
                    "deadline = ?, " +
                    "create_date = ?, " +
                    "status_id = (SELECT id FROM task_status WHERE status = ?) " +
                    "WHERE task.id = ?;";
    private static final String UPDATE_TASK_STATUS =
            "UPDATE task " +
                    "SET status_id = (SELECT id FROM task_status WHERE status = ?) " +
                    "WHERE task.id = ?;";


    @Autowired
    ConnectionHolder connectionHolder;
    @Autowired
    MapperFromResultSetToEntity<Task> taskMapper;

    @SneakyThrows
    @Override
    public Optional<Task> getById(Task task) {
        Optional<Task> optionalTask = Optional.empty();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_TASK_BY_ID)) {
            statement.setLong(1, task.getId());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalTask = taskMapper.map(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionHolder.releaseConnection();
        }
        return optionalTask;
    }

    @SneakyThrows
    @Override
    public Task insert(Task task) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_TASK, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, task.getTitle());
            statement.setString(2, task.getDescription());
            statement.setBigDecimal(3, task.getPrice());
            statement.setDate(4, new Date(task.getDeadline().getTime()));
            statement.setDate(5, new Date(task.getCreateDate().getTime()));
            statement.setLong(6, task.getCustomer().getId());
            int row = statement.executeUpdate();
            if (row == 1) {
                ResultSet resultSet = statement.getGeneratedKeys();
                resultSet.next();
                task.setId(resultSet.getLong(1));
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return task;
    }

    @SneakyThrows
    @Override
    public Task update(Task newTask, Task oldTask) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TASK)) {
            statement.setString(1, newTask.getTitle());
            statement.setString(2, newTask.getDescription());
            statement.setBigDecimal(3, newTask.getPrice());
            statement.setDate(4, new Date(newTask.getDeadline().getTime()));
            statement.setDate(5, new Date(newTask.getCreateDate().getTime()));
            statement.setString(6, newTask.getStatus().getStatus());
            statement.setLong(7, newTask.getId());
            int row = statement.executeUpdate();
            if (row == 1) {
                newTask.setId(oldTask.getId());
                newTask.setCustomer(oldTask.getCustomer());
                newTask.setStatus(oldTask.getStatus());
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return newTask;
    }

    @SneakyThrows
    @Override
    public Task updateTaskStatus(Task task) {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_TASK_STATUS)) {
            statement.setString(1, task.getStatus().getStatus());
            statement.setLong(2, task.getId());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return task;
    }

    @SneakyThrows
    @Override
    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        Optional<Task> optionalTask;
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                optionalTask = taskMapper.map(resultSet);
                optionalTask.ifPresent(tasks::add);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connectionHolder.releaseConnection();
        }
        return tasks;
    }


    @Override
    public boolean delete(Task task) {
        return false;
    }
}
