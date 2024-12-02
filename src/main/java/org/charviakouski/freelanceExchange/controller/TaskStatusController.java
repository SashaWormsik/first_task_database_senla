package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskStatuses")
@RequiredArgsConstructor
public class TaskStatusController {

    private final TaskStatusService taskStatusService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TaskStatusDto> getAll() {
        return taskStatusService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskStatusDto getById(@PathVariable long id) {
        return taskStatusService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public TaskStatusDto insert(@RequestBody TaskStatusDto taskStatusDto) {
        return taskStatusService.insert(taskStatusDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TaskStatusDto update(@PathVariable long id, @RequestBody TaskStatusDto taskStatusDto) {
        return taskStatusService.update(id, taskStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(@PathVariable long id) {
        taskStatusService.delete(id);
    }
}
