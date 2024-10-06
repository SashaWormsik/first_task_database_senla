package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskStatuses")
public class TaskStatusController {
    @Autowired
    private TaskStatusService taskStatusService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TaskStatusDto> getAll() {
        return taskStatusService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDto getById(@PathVariable(name = "id") long id) {
        return taskStatusService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatusDto insert(@RequestBody TaskStatusDto taskStatusDto) {
        return taskStatusService.insert(taskStatusDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDto update(@PathVariable(name = "id") long id, @RequestBody TaskStatusDto taskStatusDto) {
        taskStatusDto.setId(id);
        return taskStatusService.update(taskStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        taskStatusService.delete(id);
    }
}
