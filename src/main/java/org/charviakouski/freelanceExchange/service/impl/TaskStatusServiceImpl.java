package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskStatusServiceImpl implements TaskStatusService {
    @Autowired
    private TaskStatusRepository taskStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<TaskStatusDto> getAll() {
        return null;
    }

    @Override
    public TaskStatusDto getById(TaskStatusDto taskStatusDto) {
        return null;
    }

    @Override
    public boolean insert(TaskStatusDto taskStatusDto) {
        return false;
    }

    @Override
    public boolean update(TaskStatusDto taskStatusDto) {
        return false;
    }

    @Override
    public boolean delete(TaskStatusDto taskStatusDto) {
        return false;
    }
}
