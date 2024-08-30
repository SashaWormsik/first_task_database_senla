package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Role;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;

import java.util.List;

public interface TaskStatusRepository {
    List<TaskStatus> getAll();

    TaskStatus getById(TaskStatus taskStatus);

    boolean insert(TaskStatus taskStatus);

    boolean update(TaskStatus taskStatus);

    boolean delete(TaskStatus taskStatus);
}
