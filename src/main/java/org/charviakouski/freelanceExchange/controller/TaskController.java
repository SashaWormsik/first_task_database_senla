package org.charviakouski.freelanceExchange.controller;

import lombok.SneakyThrows;
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
        TaskDto taskDto = taskService.getById(entityMapper.fromJsonToDto(jsonTaskId, TaskDto.class));
        return entityMapper.fromDtoToJson(taskDto);
    }

    public String insert(String jsonTask) {
        TaskDto taskDto = taskService.insert(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
        return entityMapper.fromDtoToJson(taskDto);
    }

    public String update(String jsonTask) {
        TaskDto taskDto = taskService.update(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
        return entityMapper.fromDtoToJson(taskDto);
    }

    public boolean delete(String jsonTask) {
        return taskService.delete(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
    }

    public String getAllTaskByTitle(String jsonTask) {
        TaskDto taskDto = entityMapper.fromJsonToDto(jsonTask, TaskDto.class);
        return entityMapper.fromDtoToJson(taskService.getAllTaskByTitle(taskDto));
    }

    public String getAllTaskByPrice(String jsonTask) {
        TaskDto taskDto = entityMapper.fromJsonToDto(jsonTask, TaskDto.class);
        return entityMapper.fromDtoToJson(taskService.getAllTaskByPrice(taskDto));
    }
}
