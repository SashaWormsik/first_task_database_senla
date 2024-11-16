package org.charviakouski.freelanceExchange.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.charviakouski.freelanceExchange.exception.MyBadRequestExseption;
import org.charviakouski.freelanceExchange.exception.ServiceException;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TaskStatusServiceImpl implements TaskStatusService {

    private final TaskStatusRepository taskStatusRepository;
    private final EntityMapper entityMapper;

    @Override
    public TaskStatusDto insert(TaskStatusDto taskStatusDto) {
        log.info("Insert new TaskStatus with status {}", taskStatusDto.getStatus());
        TaskStatus taskStatus = entityMapper.fromDtoToEntity(taskStatusDto, TaskStatus.class);
        return entityMapper.fromEntityToDto(taskStatusRepository.save(taskStatus), TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto update(long id, TaskStatusDto taskStatusDto) {
        log.info("Update TaskStatus with ID {}", id);
        if (!taskStatusRepository.existsById(id)) {
            log.info("TaskStatus with ID {} does not exist", id);
            throw new MyBadRequestExseption("TaskStatus with ID " + id + " does not exist");
        }
        TaskStatus taskStatus = entityMapper.fromDtoToEntity(taskStatusDto, TaskStatus.class);
        return entityMapper.fromEntityToDto(taskStatusRepository.save(taskStatus), TaskStatusDto.class);
    }

    @Override
    public TaskStatusDto getById(Long id) {
        log.info("Get TaskStatus with ID {}", id);
        TaskStatus taskStatus = taskStatusRepository.findById(id)
                .orElseThrow(() -> {
                    log.info("TaskStatus with ID {} not found", id);
                    return new MyBadRequestExseption("TaskStatus not found");
                });
        return entityMapper.fromEntityToDto(taskStatus, TaskStatusDto.class);
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
