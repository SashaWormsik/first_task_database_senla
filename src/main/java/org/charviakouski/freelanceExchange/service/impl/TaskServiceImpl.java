package org.charviakouski.freelanceExchange.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public TaskDto insert(TaskDto taskDto) {
        log.info("insert new Task with title {}", taskDto.getTitle());
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.create(task), TaskDto.class);
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        log.info("update Task with title {}", taskDto.getTitle());
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.update(task), TaskDto.class);
    }

    @Override
    public TaskDto getById(Long id) {
        Optional<Task> optionalTask = taskRepository.getById(id);
        if (optionalTask.isEmpty()) {
            log.info("task with ID {} not found", id);
            throw new ServiceException("Task not found");
        }
        return entityMapper.fromEntityToDto(optionalTask.get(), TaskDto.class);
    }

    @Override
    public List<TaskDto> getAll() {
        log.info("get ALL task");
        return taskRepository.getAll().stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }


    @Override
    public boolean delete(Long id) {
        log.info("delete task with ID {}", id);
        taskRepository.delete(id);
        return taskRepository.getById(id).isEmpty();
    }

    @Override
    public List<TaskDto> getAllTaskByTitle(String title) {
        log.info("get ALL task with title like as {}", title);
        List<Task> taskList = taskRepository.getAllTasksByTitle(title);
        return taskList.stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }

    @Override
    public List<TaskDto> getAllTaskByPrice(BigDecimal price) {
        log.info("get ALL task with price = {}", price);
        List<Task> taskList = taskRepository.getAllTasksByPrice(price);
        return taskList.stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }

    @Override
    public TaskDto getTaskByIdGraph(TaskDto taskDto) {
        log.info("get task by ID with GRAPH= {}", taskDto.getId());
        Optional<Task> optionalTask = taskRepository.getTaskByIdGraph(taskDto.getId());
        if (optionalTask.isEmpty()) {
            log.info("task with ID {} not found (GRAPH)", taskDto.getId());
            throw new ServiceException("Task not found");
        }
        return entityMapper.fromEntityToDto(optionalTask.get(), TaskDto.class);
    }
}
