package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    EntityMapper entityMapper;

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.getAll().stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto getById(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.getById(task), TaskDto.class);
    }

    @Override
    public boolean insert(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return taskRepository.insert(task);
    }

    @Override
    public boolean update(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return taskRepository.update(task);
    }

    @Override
    public boolean delete(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return taskRepository.delete(task);
    }
}
