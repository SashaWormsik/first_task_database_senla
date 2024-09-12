package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.TaskStatus;

import java.util.List;
import java.util.Optional;

public interface TaskStatusRepository {
    Optional<TaskStatus> getByStatusName(TaskStatus taskStatus);

    List<TaskStatus> getAll();

    Optional<TaskStatus> getById(TaskStatus taskStatus);

    TaskStatus insert(TaskStatus taskStatus);

    TaskStatus update(TaskStatus newTaskStatus, TaskStatus oldTaskStatus);

    boolean delete(TaskStatus taskStatus);
}
