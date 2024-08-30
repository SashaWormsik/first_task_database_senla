package org.charviakouski.freelanceExchange.controller;

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

    public String getById(String jsonTaskId) {
        return entityMapper.fromDtoToJson(taskService.getById(entityMapper.fromJsonToDto(jsonTaskId, TaskDto.class)));
    }

    public boolean insert(String jsonTask) {
        return taskService.insert(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
    }

    public boolean update(String jsonTask) {
        return taskService.update(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
    }

    public boolean delete(String jsonTask) {
        return taskService.delete(entityMapper.fromJsonToDto(jsonTask, TaskDto.class));
    }
}
