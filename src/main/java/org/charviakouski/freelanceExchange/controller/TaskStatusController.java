package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusController {
    @Autowired
    private TaskStatusService taskStatusService;
    @Autowired
    private EntityMapper entityMapper;

    public String getAll() {
        return entityMapper.fromDtoToJson(taskStatusService.getAll());
    }

    public String getById(String jsonTaskStatusId) {
        TaskStatusDto taskStatusDto = taskStatusService.getById(entityMapper.fromJsonToDto(jsonTaskStatusId, TaskStatusDto.class));
        return entityMapper.fromDtoToJson(taskStatusDto);
    }

    public String insert(String jsonTaskStatus) {
        TaskStatusDto taskStatusDto = taskStatusService.insert(entityMapper.fromJsonToDto(jsonTaskStatus, TaskStatusDto.class));
        return entityMapper.fromDtoToJson(taskStatusDto);
    }

    public String update(String jsonTaskStatus) {
        TaskStatusDto taskStatusDto = taskStatusService.update(entityMapper.fromJsonToDto(jsonTaskStatus, TaskStatusDto.class));
        return entityMapper.fromDtoToJson(taskStatusDto);
    }

    public boolean delete(String jsonTaskStatus) {
        return taskStatusService.delete(entityMapper.fromJsonToDto(jsonTaskStatus, TaskStatusDto.class));

    }
}
