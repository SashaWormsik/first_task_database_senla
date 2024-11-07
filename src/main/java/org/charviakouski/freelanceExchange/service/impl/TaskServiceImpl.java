package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EntityMapper entityMapper;

    @Override
    public TaskDto insert(TaskDto taskDto) {
        log.info("insert new Task with title {}", taskDto.getTitle());
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.save(task), TaskDto.class);
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        log.info("update Task with title {}", taskDto.getTitle());
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.save(task), TaskDto.class);
    }

    @Override
    public TaskDto getById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            log.info("task with ID {} not found", id);
            throw new ServiceException("Task not found");
        }
        return entityMapper.fromEntityToDto(optionalTask.get(), TaskDto.class);
    }

    @Override
    public List<TaskDto> getAll() {
        log.info("get ALL task");
        return taskRepository.findAll().stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .toList();
    }


    @Override
    public boolean delete(Long id) {
        log.info("delete task with ID {}", id);
        taskRepository.deleteById(id);
        return taskRepository.existsById(id);
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
}
