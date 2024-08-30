package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAll();

    TaskDto getById(TaskDto taskDto);

    boolean insert(TaskDto taskDto);

    boolean update(TaskDto taskDto);

    boolean delete(TaskDto taskDto);
}
