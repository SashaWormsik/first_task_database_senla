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
        taskStatusService.getAll();
        return null;
    }

    public String getById(String jsonTaskStatusId) {
        taskStatusService.getById(entityMapper.fromJsonToDto(jsonTaskStatusId, TaskStatusDto.class));
        return null;
    }

    public boolean insert(String jsonTaskStatus) {
        taskStatusService.insert(entityMapper.fromJsonToDto(jsonTaskStatus, TaskStatusDto.class));
        return false;
    }

    public boolean update(String jsonTaskStatus) {
        taskStatusService.update(entityMapper.fromJsonToDto(jsonTaskStatus, TaskStatusDto.class));
        return false;
    }

    public boolean delete(String jsonTaskStatus) {
        taskStatusService.delete(entityMapper.fromJsonToDto(jsonTaskStatus, TaskStatusDto.class));
        return false;
    }
}
