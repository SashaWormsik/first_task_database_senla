package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatusDto> getAll();

    TaskStatusDto getById(Long id);

    TaskStatusDto insert(TaskStatusDto taskStatusDto);

    TaskStatusDto update(TaskStatusDto taskStatusDto);

    boolean delete(Long id);
}
