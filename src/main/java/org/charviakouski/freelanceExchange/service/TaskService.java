package org.charviakouski.freelanceExchange.service;


import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaskService {
    Page<TaskDto> getAll(int page, int size, String sort);

    TaskDto getById(Long id);

    TaskDto insert(TaskDto taskDto);

    TaskDto update(long id, TaskDto taskDto);

    boolean delete(Long id);

    Page<TaskDto> searchTask(String title, List<String> categoriesName, int page, int size, String sort);

    Page<TaskDto> getCompanyTasks(long id, int page, int size, String sort);

}
