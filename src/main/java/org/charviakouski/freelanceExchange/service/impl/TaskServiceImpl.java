package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<TaskDto> getAll() {
        return null;
    }

    @Override
    public TaskDto insert(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto getById(TaskDto taskDto) {
        return null;
    }


    @Override
    public boolean delete(TaskDto taskDto) {
        return false;
    }
}
