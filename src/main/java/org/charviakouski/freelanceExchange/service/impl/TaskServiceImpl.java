package org.charviakouski.freelanceExchange.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TaskServiceImpl implements TaskService {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public TaskDto insert(TaskDto taskDto) {
        LOGGER.log(Level.INFO, "insert new Task with title {}", taskDto.getTitle());
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.create(task), TaskDto.class);
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        LOGGER.log(Level.INFO, "update Task with title {}", taskDto.getTitle());
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.update(task), TaskDto.class);
    }

    @Override
    public TaskDto getById(TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.getById(taskDto.getId());
        if (optionalTask.isEmpty()) {
            LOGGER.log(Level.INFO, "task with ID {} not found", taskDto.getId());
            throw new ServiceException("Task not found");
        }
        return entityMapper.fromEntityToDto(optionalTask.get(), TaskDto.class);
    }

    @Override
    public List<TaskDto> getAll() {
        LOGGER.log(Level.INFO, "get ALL task");
        return taskRepository.getAll().stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }


    @Override
    public boolean delete(TaskDto taskDto) {
        LOGGER.log(Level.INFO, "delete task with title {}", taskDto.getTitle());
        taskRepository.delete(taskDto.getId());
        return taskRepository.getById(taskDto.getId()).isEmpty();
    }

    @Override
    public List<TaskDto> getAllTaskByTitle(TaskDto taskDto) {
        LOGGER.log(Level.INFO, "get ALL task with title like as {}", taskDto.getTitle());
        List<Task> taskList = taskRepository.getAllTasksByTitle(taskDto.getTitle());
        return taskList.stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }

    @Override
    public List<TaskDto> getAllTaskByPrice(TaskDto taskDto) {
        LOGGER.log(Level.INFO, "get ALL task with price = {}", taskDto.getPrice());
        List<Task> taskList = taskRepository.getAllTasksByPrice(taskDto.getPrice());
        return taskList.stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }

    public TaskDto getTaskByIdGraph(TaskDto taskDto){
        LOGGER.log(Level.INFO, "get task by ID = {}", taskDto.getId());
        Optional<Task> optionalTask = taskRepository.getTaskByIdGraph(taskDto.getId());
        if (optionalTask.isEmpty()) {
            LOGGER.log(Level.INFO, "task with ID {} not found", taskDto.getId());
            throw new ServiceException("Task not found");
        }
        return entityMapper.fromEntityToDto(optionalTask.get(), TaskDto.class);
    }
}
