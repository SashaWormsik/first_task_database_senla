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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<TaskStatusDto> getAll() {
        return taskStatusService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public TaskStatusDto getById(@PathVariable(name = "id") long id) {
        return taskStatusService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public TaskStatusDto insert(@RequestBody TaskStatusDto taskStatusDto) {
        return taskStatusService.insert(taskStatusDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public TaskStatusDto update(@PathVariable(name = "id") long id, @RequestBody TaskStatusDto taskStatusDto) {
        taskStatusDto.setId(id);
        return taskStatusService.update(taskStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name = "id") long id) {
        taskStatusService.delete(id);
    }
}
