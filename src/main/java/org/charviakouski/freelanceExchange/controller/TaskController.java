package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;


    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public List<TaskDto> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public TaskDto getById(@PathVariable long id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public TaskDto insert(@RequestBody TaskDto taskDto) {
        return taskService.insert(taskDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('USER')")
    public TaskDto update(@PathVariable long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return taskService.update(taskDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('USER')")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }

    @GetMapping(value = "/title")
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public List<TaskDto> getAllTaskByTitle(@RequestParam String title) {
        return taskService.getAllTaskByTitle(title);
    }

    @GetMapping(value = "/price")
    @PreAuthorize("hasAnyRole({'ADMIN', 'USER'})")
    public List<TaskDto> getAllTaskByPrice(@RequestParam String price) {
        BigDecimal pr = new BigDecimal(price);
        return taskService.getAllTaskByPrice(pr);
    }
}
