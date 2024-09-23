package org.charviakouski.freelanceExchange.service.impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.model.dto.UserInfoDto;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.entity.UserInfo;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class TaskStatusServiceImpl implements TaskStatusService {
    private static final Logger LOGGER = LogManager.getLogger();
    @Autowired
    private TaskStatusRepository taskStatusRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public TaskStatusDto insert(TaskStatusDto taskStatusDto) {
        LOGGER.log(Level.INFO, "insert new TaskStatus with status {}", taskStatusDto.getStatus());
        TaskStatus taskStatus = entityMapper.fromDtoToEntity(taskStatusDto, TaskStatus.class);
        return entityMapper.fromEntityToDto(taskStatusRepository.create(taskStatus), TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto update(TaskStatusDto taskStatusDto) {
        LOGGER.log(Level.INFO, "update TaskStatus with status {}", taskStatusDto.getStatus());
        TaskStatus taskStatus = entityMapper.fromDtoToEntity(taskStatusDto, TaskStatus.class);
        return entityMapper.fromEntityToDto(taskStatusRepository.update(taskStatus), TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto getById(TaskStatusDto taskStatusDto) {
        Optional<TaskStatus> optionalTaskStatus = taskStatusRepository.getById(taskStatusDto.getId());
        if (optionalTaskStatus.isEmpty()) {
            LOGGER.log(Level.INFO, "taskStatus with ID {} not found", taskStatusDto.getId());
            throw new ServiceException("TaskStatus not found");
        }
        return entityMapper.fromEntityToDto(optionalTaskStatus.get(), TaskStatusDto.class);
    }

    @Override
    public List<TaskStatusDto> getAll() {
        LOGGER.log(Level.INFO, "get ALL taskStatus");
        return taskStatusRepository.getAll().stream()
                .map(taskStatus -> entityMapper.fromEntityToDto(taskStatus, TaskStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(TaskStatusDto taskStatusDto) {
        LOGGER.log(Level.INFO, "delete taskStatus with status {}", taskStatusDto.getStatus());
        taskStatusRepository.delete(taskStatusDto.getId());
        return taskStatusRepository.getById(taskStatusDto.getId()).isEmpty();
    }
}
