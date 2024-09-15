package org.charviakouski.freelanceExchange.service.impl;

import org.charviakouski.freelanceExchange.annotation.Transactional;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
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
    private UserInfoRepository userInfoRepository;
    @Autowired
    TaskStatusRepository taskStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public List<TaskDto> getAll() {
        List<Task> taskList = taskRepository.getAll();
        taskList.forEach(task -> task.setCategories(categoryRepository.getAllCategoryForTask(task).orElse(null)));
        return taskList.stream()
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TaskDto insert(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        task.setCustomer(userInfoRepository.insert(task.getCustomer()) );
        task = taskRepository.insert(task);
        categoryRepository.insertInTaskCategory(task.getCategories(), task);
        task.setCategories(categoryRepository.getAllCategoryForTask(task).orElse(null));
        return entityMapper.fromEntityToDto(task, TaskDto.class);
    }

    @Transactional
    @Override
    public TaskDto update(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        List<Category> categories = task.getCategories();
        Optional<Task> oldTask = taskRepository.getById(task);
        if (!oldTask.isPresent()) {
            throw new RuntimeException("Объект отсутствует, а значит обновить невозможно");
        }
        categoryRepository.deleteInTaskCategory(task);
        categoryRepository.insertInTaskCategory(categories, task);
        task.setCategories(categoryRepository.getAllCategoryForTask(task).orElse(null));
        return entityMapper.fromEntityToDto(taskRepository.update(task, oldTask.get()), TaskDto.class);
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
            task = optionalTask.get();
            task.setCategories(optionalCategory.get());
        }
        return entityMapper.fromEntityToDto(task, TaskDto.class);
    }


    @Override
    public boolean delete(TaskDto taskDto) {
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return taskRepository.delete(task);
    }
}
