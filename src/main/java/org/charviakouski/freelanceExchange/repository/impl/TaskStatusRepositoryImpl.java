package org.charviakouski.freelanceExchange.repository.impl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.connection.ConnectionHolder;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class TaskStatusRepositoryImpl implements TaskStatusRepository {

    private static final String SELECT_CATEGORY_BY_STATUS_NAME =
            "SELECT id AS task_status_id, status AS task_status_status " +
                    "FROM task_status WHERE status = ?";

    @Autowired
    private ConnectionHolder connectionHolder;
    @Autowired
    private MapperFromResultSetToEntity<TaskStatus> taskStatusMapper;

    @SneakyThrows
    @Override
    public Optional<TaskStatus> getByStatusName(TaskStatus taskStatus) {
        Optional<TaskStatus> optionalTaskStatus = Optional.empty();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_STATUS_NAME)) {
            statement.setObject(1, taskStatus.getStatus());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalTaskStatus = taskStatusMapper.map(resultSet);
            }
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        } finally {
            connectionHolder.releaseConnection();
        }
        return optionalTaskStatus;
    }

    @Override
    public List<TaskStatus> getAll() {
        return null;
    }

    @Override
    public Optional<TaskStatus> getById(TaskStatus taskStatus) {
        return null;
    }

    @Override
    public TaskStatus insert(TaskStatus taskStatus) {
        return null;
    }

    @Override
    public TaskStatus update(TaskStatus newTaskStatus, TaskStatus oldTaskStatus) {
        return null;
    }

    @Override
    public boolean delete(TaskStatus taskStatus) {
        return false;
    }
}
