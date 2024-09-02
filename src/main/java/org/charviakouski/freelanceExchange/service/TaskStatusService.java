package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatusDto> getAll();

    TaskStatusDto getById(TaskStatusDto taskStatusDto);

    TaskStatusDto insert(TaskStatusDto taskStatusDto);

    TaskStatusDto update(TaskStatusDto taskStatusDto);

    boolean delete(TaskStatusDto taskStatusDto);
}
