package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<TaskDto> getAll() {
        return taskRepository.getAll().stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskDto insert(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        if (taskRepository.getById(task).isPresent()) {
            throw new RuntimeException("Уже есть такой объект");
        }
        return entityMapper.fromEntityToDto(taskRepository.insert(task), TaskDto.class);
    }


    @Override
    public TaskDto update(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        Optional<Task> optionalTask = taskRepository.getById(task);
        if (!optionalTask.isPresent()) {
            throw new RuntimeException("Объект отсутствует, а значит обновить невозможно");
        }
        return entityMapper.fromEntityToDto(taskRepository.update(task, optionalTask.get()), TaskDto.class);
    }

    @Override
    public TaskDto getById(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        Optional<Task> optionalTask = taskRepository.getById(task);
        Optional<List<Category>> optionalCategory = categoryRepository.getAllCategoryForTask(task);
        if (!optionalTask.isPresent()) {
            throw new RuntimeException("Объект не существует!!!");
        }
        if (optionalCategory.isPresent()) {
            List<Category> categoryList = optionalCategory.get();
            task = optionalTask.get();
            task.setCategories(categoryList);
        }
        return entityMapper.fromEntityToDto(task, TaskDto.class);
    }


    @Override
    public boolean delete(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return taskRepository.delete(task);
    }
}
