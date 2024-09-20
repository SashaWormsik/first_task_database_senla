package org.charviakouski.freelanceExchange.service;


import org.charviakouski.freelanceExchange.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAll();

    TaskDto getById(TaskDto taskDto);

    TaskDto insert(TaskDto taskDto);

    TaskDto update(TaskDto taskDto);

    boolean delete(TaskDto taskDto);

    List<TaskDto> getAllTaskByTitle(TaskDto taskDto);
    List<TaskDto> getAllTaskByPrice(TaskDto taskDto);
}
