package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;
    private final EntityMapper entityMapper;

    @Override
    public TaskStatusDto insert(TaskStatusDto taskStatusDto) {
        log.info("insert new TaskStatus with status {}", taskStatusDto.getStatus());
        TaskStatus taskStatus = entityMapper.fromDtoToEntity(taskStatusDto, TaskStatus.class);
        return entityMapper.fromEntityToDto(taskStatusRepository.save(taskStatus), TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto update(TaskStatusDto taskStatusDto) {
        log.info("update TaskStatus with status {}", taskStatusDto.getStatus());
        TaskStatus taskStatus = entityMapper.fromDtoToEntity(taskStatusDto, TaskStatus.class);
        return entityMapper.fromEntityToDto(taskStatusRepository.save(taskStatus), TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto getById(Long id) {
        Optional<TaskStatus> optionalTaskStatus = taskStatusRepository.findById(id);
        if (optionalTaskStatus.isEmpty()) {
            log.info("taskStatus with ID {} not found", id);
            throw new ServiceException("TaskStatus not found");
        }
        return entityMapper.fromEntityToDto(optionalTaskStatus.get(), TaskStatusDto.class);
    }

    @Override
    public List<TaskStatusDto> getAll() {
        log.info("get ALL taskStatus");
        return taskStatusRepository.findAll().stream()
                .map(taskStatus -> entityMapper.fromEntityToDto(taskStatus, TaskStatusDto.class))
                .toList();
    }

    @Override
    public boolean delete(Long id) {
        log.info("delete taskStatus with ID {}", id);
        taskStatusRepository.deleteById(id);
        return taskStatusRepository.existsById(id);
    }
}
