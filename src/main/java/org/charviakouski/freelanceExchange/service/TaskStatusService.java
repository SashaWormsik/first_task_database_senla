package org.charviakouski.freelanceExchange.service;

import org.charviakouski.freelanceExchange.model.dto.FeedBackDto;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;

import java.util.List;

public interface TaskStatusService {
    List<TaskStatusDto> getAll();

    TaskStatusDto getById(TaskStatusDto taskStatusDto);

    boolean insert(TaskStatusDto taskStatusDto);

    boolean update(TaskStatusDto taskStatusDto);

    boolean delete(TaskStatusDto taskStatusDto);
}
