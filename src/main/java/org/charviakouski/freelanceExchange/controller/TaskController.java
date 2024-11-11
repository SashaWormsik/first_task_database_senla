package org.charviakouski.freelanceExchange.controller;

import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<TaskDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                @RequestParam(name = "size", defaultValue = "2") int size,
                                @RequestParam(name = "sort", defaultValue = "title") String sort) {
        return taskService.getAll(page, size, sort);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public TaskDto getById(@PathVariable long id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('CUSTOMER')")
    public TaskDto insert(@RequestBody TaskDto taskDto) {
        return taskService.insert(taskDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public TaskDto update(@PathVariable long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return taskService.update(taskDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('CUSTOMER')")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }

    @GetMapping(value = "/title")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<TaskDto> getAllTaskByTitle(@RequestParam String title,
                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "size", defaultValue = "2") int size) {
        return taskService.getAllTaskByTitle(title, page, size);
    }

    @GetMapping(value = "/price")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<TaskDto> getAllTaskByPrice(@RequestParam BigDecimal price,
                                           @RequestParam(name = "page", defaultValue = "1") int page,
                                           @RequestParam(name = "size", defaultValue = "2") int size) {
        return taskService.getAllTaskByPrice(price, page, size);
    }
}
