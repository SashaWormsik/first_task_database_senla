package org.charviakouski.freelanceExchange.model.mapper.impl;

import org.charviakouski.freelanceExchange.exception.RepositoryException;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
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
        UserInfo userInfo = new UserInfo();
        TaskStatus taskStatus = new TaskStatus();
        Optional<Task> optionalTask;
        try {
            task.setId(resultSet.getLong("task_id"));
            task.setTitle(resultSet.getString("task_title"));
            task.setDescription(resultSet.getString("task_description"));
            task.setPrice(resultSet.getBigDecimal("task_price"));
            task.setDeadline(resultSet.getDate("task_deadline"));
            task.setCreateDate(resultSet.getDate("task_create_date"));
            // For TaskStatus
            taskStatus.setId(resultSet.getLong("task_status_id"));
            taskStatus.setStatus(resultSet.getString("task_status_status"));
            // For UserInfo
            userInfo.setId(resultSet.getLong("user_info_id"));
            userInfo.setName(resultSet.getString("user_info_name"));
            userInfo.setSurname(resultSet.getString("user_info_surname"));
            userInfo.setProfession(resultSet.getString("user_info_profession"));
            userInfo.setWorkExperience(resultSet.getInt("user_info_work_experience"));
            userInfo.setDescription(resultSet.getString("user_info_description"));
            task.setStatus(taskStatus);
            task.setCustomer(userInfo);
            optionalTask = Optional.of(task);
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return optionalTask;
    }
}
