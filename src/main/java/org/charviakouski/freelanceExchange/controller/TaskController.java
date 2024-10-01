package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private EntityMapper entityMapper;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll() {
        List<TaskDto> taskDtoList = taskService.getAll();
        return ResponseEntity.ok().body(taskDtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable(name = "id") long id) {
        TaskDto taskDto = taskService.getById(id);
        return ResponseEntity.ok().body(taskDto);
    }

    @PostMapping
    public ResponseEntity<TaskDto> insert(@RequestBody TaskDto taskDto) {
        TaskDto newTaskDto = taskService.insert(taskDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTaskDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TaskDto> update(@PathVariable(name = "id") long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        TaskDto updatedTaskDto = taskService.update(taskDto);
        return ResponseEntity.ok().body(updatedTaskDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/title")
    public ResponseEntity<List<TaskDto>> getAllTaskByTitle(@RequestParam(name = "title") String title) {
        List<TaskDto> taskDtoList = taskService.getAllTaskByTitle(title);
        return ResponseEntity.ok().body(taskDtoList);
    }

    @GetMapping(value = "/price")
    public ResponseEntity<List<TaskDto>> getAllTaskByPrice(@RequestParam(name = "price") String price) {
        BigDecimal pr = new BigDecimal(price);
        List<TaskDto> taskDtoList = taskService.getAllTaskByPrice(pr);
        return ResponseEntity.ok().body(taskDtoList);
    }
}
