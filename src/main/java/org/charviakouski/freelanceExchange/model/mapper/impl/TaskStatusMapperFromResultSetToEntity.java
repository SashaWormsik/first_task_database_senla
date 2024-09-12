package org.charviakouski.freelanceExchange.model.mapper.impl;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class TaskStatusMapperFromResultSetToEntity implements MapperFromResultSetToEntity<TaskStatus> {
    @Override
    public Optional<TaskStatus> map(ResultSet resultSet) {
        TaskStatus taskStatus = new TaskStatus();
        Optional<TaskStatus> optionalTaskStatus;
        try {
            taskStatus.setId(resultSet.getLong("task_status_id"));
            taskStatus.setStatus(resultSet.getString("task_status_status"));
            optionalTaskStatus = Optional.of(taskStatus);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return optionalTaskStatus;
    }
}
