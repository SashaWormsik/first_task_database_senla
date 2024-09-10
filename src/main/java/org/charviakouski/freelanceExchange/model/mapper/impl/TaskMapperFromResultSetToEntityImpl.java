package org.charviakouski.freelanceExchange.model.mapper.impl;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.MapperFromResultSetToEntity;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class TaskMapperFromResultSetToEntityImpl implements MapperFromResultSetToEntity<Task> {
    @Override
    public Optional<Task> map(ResultSet resultSet) {
        Task task = new Task();
        Optional<Task> optionalTask;
        try {
            task.setId(resultSet.getLong("id"));
            task.setTitle(resultSet.getString("title"));
            task.setDescription(resultSet.getString("description"));
            task.setPrice(resultSet.getBigDecimal("price"));
            task.setDeadline(resultSet.getDate("deadline"));
            task.setCreateDate(resultSet.getDate("create_date"));
            optionalTask = Optional.of(task);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return optionalTask;
    }
}
