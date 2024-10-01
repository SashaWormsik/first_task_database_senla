package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskStatuses")
public class TaskStatusController {
    @Autowired
    private TaskStatusService taskStatusService;

    @GetMapping
    public ResponseEntity<List<TaskStatusDto>> getAll() {
        List<TaskStatusDto> taskStatusListDto = taskStatusService.getAll();
        return ResponseEntity.ok().body(taskStatusListDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskStatusDto> getById(@PathVariable(name = "id") long id) {
        TaskStatusDto taskStatusDto = taskStatusService.getById(id);
        return ResponseEntity.ok().body(taskStatusDto);
    }

    @PostMapping
    public ResponseEntity<TaskStatusDto> insert(@RequestBody TaskStatusDto taskStatusDto) {
        TaskStatusDto newTaskStatusDto = taskStatusService.insert(taskStatusDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTaskStatusDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskStatusDto> update(@PathVariable(name = "id") long id, @RequestBody TaskStatusDto taskStatusDto) {
        taskStatusDto.setId(id);
        TaskStatusDto updatedTaskStatusDto = taskStatusService.update(taskStatusDto);
        return ResponseEntity.ok().body(updatedTaskStatusDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        taskStatusService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
