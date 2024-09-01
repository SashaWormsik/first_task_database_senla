package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;

import java.util.List;

public interface TaskService {
    List<TaskDto> getAll();

    TaskDto getById(TaskDto taskDto) throws ServiceException;

    TaskDto insert(TaskDto taskDto) throws ServiceException;

    TaskDto update(TaskDto taskDto) throws ServiceException;

    boolean delete(TaskDto taskDto);
}
