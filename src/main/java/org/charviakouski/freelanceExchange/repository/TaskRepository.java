package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    Task updateTaskStatus(Task task);

    List<Task> getAll();

    Optional<Task> getById(Task task);

    Task insert(Task task);

    Task update(Task newTask, Task oldTask);

    boolean delete(Task task);
}
