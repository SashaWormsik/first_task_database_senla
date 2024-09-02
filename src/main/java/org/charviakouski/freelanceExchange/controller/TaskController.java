package org.charviakouski.freelanceExchange.controller;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.exception.ControllerException;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        return entityMapper.fromDtoToJson(taskService.getAll());
    }

    @SneakyThrows
    public String getById(String jsonTaskId) {
        try {
            TaskDto taskDto = taskService.getById(entityMapper.fromJsonToDto(jsonTaskId, TaskDto.class));
            return entityMapper.fromDtoToJson(taskDto);
        } catch (ServiceException e) {
            throw new ControllerException("Не удалось найти объект \n" + e.getMessage());
        }
    }

    public String insert(String jsonTask) {
        try {
            TaskDto taskDto = taskService.insert(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
            return entityMapper.fromDtoToJson(taskDto);
        } catch (ServiceException e) {
            throw new ControllerException("Не удалось вставить объект \n" + e.getMessage());
        }
    }

    public String update(String jsonTask) {
        try {
            TaskDto taskDto = taskService.update(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
            return entityMapper.fromDtoToJson(taskDto);
        } catch (ServiceException e) {
            throw new ControllerException("Не удалось обновить объект\n" + e.getMessage());
        }
    }

    public boolean delete(String jsonTask) {
        return taskService.delete(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
    }
}
