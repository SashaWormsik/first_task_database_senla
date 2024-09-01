package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskStatusRepositoryImpl implements TaskStatusRepository {
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
