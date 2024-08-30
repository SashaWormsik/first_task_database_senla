package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;

import java.util.List;

public interface TaskRepository {
    List<Task> getAll();

    Task getById(Task task);

    boolean insert(Task task);

    boolean update(Task task);

    boolean delete(Task task);
}
