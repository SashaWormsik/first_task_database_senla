package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskStatusRepositoryImpl implements TaskStatusRepository {
    @Override
    public List<TaskStatus> getAll() {
        return null;
    }

    @Override
    public TaskStatus getById(TaskStatus taskStatus) {
        return null;
    }

    @Override
    public boolean insert(TaskStatus taskStatus) {
        return false;
    }

    @Override
    public boolean update(TaskStatus taskStatus) {
        return false;
    }

    @Override
    public boolean delete(TaskStatus taskStatus) {
        return false;
    }
}
