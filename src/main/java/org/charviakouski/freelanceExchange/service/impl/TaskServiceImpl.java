package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.BadRequest;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Category;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.CategoryRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final CategoryRepository categoryRepository;
    private final UserInfoRepository userInfoRepository;
    private final EntityMapper entityMapper;

    @Override
    public TaskDto insert(TaskDto taskDto) {
        log.info("insert new Task with title {}", taskDto.getTitle());
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(credentialUserDetails.getId());
        if (optionalUserInfo.isEmpty()) {
            throw new ServiceException("Something has happened! User not found");
        }
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        task.setCustomer(optionalUserInfo.get());
        task.setCreateDate(new Date());
        insertActualCategoryIntoTask(task);
        task.setStatus(taskStatusRepository.findByStatus("ACTUAL")); // TODO
        return entityMapper.fromEntityToDto(taskRepository.save(task), TaskDto.class);
    }

    @Override
    public TaskDto update(TaskDto taskDto) {
        log.info("update Task with title {}", taskDto.getTitle());
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if (!credentialUserDetails.getId().equals(taskDto.getCustomer().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        insertActualCategoryIntoTask(task);
        task.setCreateDate(new Date());
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
    public Page<TaskDto> getAll(int page, int size, String sort) {
        log.info("get ALL task");
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        return taskRepository
                .findAll(pageable)
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));
    }


    @Override
    public boolean delete(Long id) {
        log.info("delete task with ID {}", id);
        Task task = taskRepository.getReferenceById(id);
        CredentialUserDetails credentialUserDetails = (CredentialUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); // TODO
        if (!credentialUserDetails.getId().equals(task.getCustomer().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        taskRepository.deleteById(id);
        return taskRepository.existsById(id);
    }

    @Override
    public Page<TaskDto> getAllTaskByTitle(String title, int page, int size) {
        log.info("get ALL task with title like as {}", title);
        Pageable pageable = PageRequest.of(page - 1, size);
        return taskRepository
                .findAllTasksByTitleContainingIgnoreCase(title, pageable)
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));
    }

    @Override
    public Page<TaskDto> getAllTaskByPrice(BigDecimal price, int page, int size) {
        log.info("get ALL task with price = {}", price);
        Pageable pageable = PageRequest.of(page - 1, size);
        return taskRepository
                .findAllTasksByPrice(price, pageable)
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));

    }

    private void insertActualCategoryIntoTask(Task task) {
        List<String> categoriesInNewTask = task.getCategories().stream()
                .map(Category::getName)
                .toList();
        List<String> allCategoryInDB = categoryRepository.getNames();
        for (String categoryName : categoriesInNewTask) {
            if (!allCategoryInDB.contains(categoryName)) {
                throw new BadRequest("There is no category named " + categoryName);
            }
        }
        task.setCategories(categoryRepository.findByNameIn(categoriesInNewTask));
    }
}
