package org.charviakouski.freelanceExchange.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<TaskDto> getAll(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                @RequestParam(name = "sort", defaultValue = "title")
                                @Pattern(regexp = "title|price|deadline") String sort) {
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
    public TaskDto insert(@Valid @RequestBody TaskDto taskDto) {
        return taskService.insert(taskDto);
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public TaskDto update(@PathVariable long id, @Valid @RequestBody TaskDto taskDto) {
        return taskService.update(id, taskDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('CUSTOMER')")
    public void delete(@PathVariable long id) {
        taskService.delete(id);
    }

    @GetMapping(value = "/search")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<TaskDto> searchTask(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) List<String> category,
                                    @RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                    @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                    @RequestParam(name = "sort", defaultValue = "title")
                                    @Pattern(regexp = "title|price|deadline") String sort) {
        return taskService.searchTask(title, category, page, size, sort);
    }

    @GetMapping(value = "/company_tasks/{id}")
    @PreAuthorize("hasAnyRole({'ADMIN', 'CUSTOMER', 'EXECUTOR'})")
    public Page<TaskDto> getCompanyTasks(@RequestParam(name = "page", defaultValue = "1") @Min(1) int page,
                                         @RequestParam(name = "size", defaultValue = "2") @Min(1) int size,
                                         @PathVariable long id,
                                         @RequestParam(name = "sort", defaultValue = "title")
                                         @Pattern(regexp = "title|price|deadline") String sort) {
        return taskService.getCompanyTasks(id, page, size, sort);
    }
}
