package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestExseption;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.entity.security.CredentialUserDetails;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.repository.UserInfoRepository;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.charviakouski.freelanceExchange.util.PrincipalUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final String TASK_ACTUAL = "ACTUAL";
    private final String TASK_NOT_ACTUAL = "NOT_ACTUAL";
    private final String TASK_EXECUTED = "EXECUTED";

    private final TaskRepository taskRepository;
    private final UserInfoRepository userInfoRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final EntityMapper entityMapper;
    private final PrincipalUtil principalUtil;

    @Override
    public TaskDto insert(TaskDto taskDto) {
        log.info("insert new Task with title {}", taskDto.getTitle());
        Optional<UserInfo> optionalUserInfo = userInfoRepository.findById(principalUtil.getCurrentUserId());
        if (optionalUserInfo.isEmpty()) {
            throw new ServiceException("Something has happened! User not found");
        }
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        task.setCustomer(optionalUserInfo.get());
        task.setCreateDate(new Date());
        task.setStatus(taskStatusRepository.findByStatus(TASK_ACTUAL));
        return entityMapper.fromEntityToDto(taskRepository.save(task), TaskDto.class);
    }

    @Override
    public TaskDto update(long id, TaskDto taskDto) {
        log.info("update Task with ID {}", id);
        if (taskRepository.existsById(id)) {
            throw new MyBadRequestExseption("Task not found with ID " + id);
        }
        if (!principalUtil.checkId(taskDto.getCustomer().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        Task task = entityMapper.fromDtoToEntity(taskDto, Task.class);
        return entityMapper.fromEntityToDto(taskRepository.save(task), TaskDto.class);
    }

    @Override
    public TaskDto getById(Long id) {
        log.info("get Task with ID {}", id);
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
        if (!principalUtil.checkId(task.getCustomer().getId())) {
            throw new AccessDeniedException("You cannot change other people's data");
        }
        taskRepository.deleteById(id);
        return taskRepository.existsById(id);
    }

    @Override
    public Page<TaskDto> searchTask(String title, List<String> categoriesName, int page, int size, String sort) {
        log.info("get ALL task with title = {} and categories = {}", title, categoriesName);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
        if (categoriesName.isEmpty()) {
            return taskRepository.findAllTasksByTitle(title, pageable)
                    .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));
        }
        return taskRepository
                .findAllByTitleAndCategory(title, categoriesName, pageable)
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));
    }

    @Override
    public Page<TaskDto> getCompanyTasks(long id, int page, int size, String sort) {
        log.info("Get all tasks Company with ID {}", id);
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sort));
       if (principalUtil.checkId(id)) {
            return taskRepository.findAllByCustomerIdAndStatusStatusIn(id, List.of(TASK_ACTUAL, TASK_EXECUTED, TASK_NOT_ACTUAL), pageable)
                    .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));
        }
        return taskRepository.findAllByCustomerIdAndStatusStatusIn(id, List.of(TASK_ACTUAL), pageable)
                .map(task -> entityMapper.fromEntityToDto(task, TaskDto.class));
    }

}
