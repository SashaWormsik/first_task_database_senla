package org.charviakouski.freelanceExchange.service;


import org.charviakouski.freelanceExchange.model.dto.TaskDto;

import java.math.BigDecimal;
import java.util.List;

public interface TaskService {
    List<TaskDto> getAll();

    TaskDto getById(Long id);

    TaskDto insert(TaskDto taskDto);

    TaskDto update(TaskDto taskDto);

    boolean delete(Long id);

    List<TaskDto> getAllTaskByTitle(String title);

    List<TaskDto> getAllTaskByPrice(BigDecimal price);

    TaskDto getTaskByIdGraph(TaskDto taskDto);
}
