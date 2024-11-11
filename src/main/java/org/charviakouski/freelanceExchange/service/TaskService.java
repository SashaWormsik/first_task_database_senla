package org.charviakouski.freelanceExchange.service;


import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface TaskService {
    Page<TaskDto> getAll(int page, int size, String sort);

    TaskDto getById(Long id);

    TaskDto insert(TaskDto taskDto);

    TaskDto update(TaskDto taskDto);

    boolean delete(Long id);

    Page<TaskDto> getAllTaskByTitle(String title, int page, int size);

    Page<TaskDto> getAllTaskByPrice(BigDecimal price, int page, int size);

}
