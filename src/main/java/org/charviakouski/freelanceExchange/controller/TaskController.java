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
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<TaskDto> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public TaskDto getById(@PathVariable(name = "id") long id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('USER')")
    public TaskDto insert(@RequestBody TaskDto taskDto) {
        return taskService.insert(taskDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public TaskDto update(@PathVariable(name = "id") long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return taskService.update(taskDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER')")
    public void delete(@PathVariable(name = "id") long id) {
        taskService.delete(id);
    }

    @GetMapping(value = "/title")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<TaskDto> getAllTaskByTitle(@RequestParam(name = "title") String title) {
        return taskService.getAllTaskByTitle(title);
    }

    @GetMapping(value = "/price")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('USER')")
    public List<TaskDto> getAllTaskByPrice(@RequestParam(name = "price") String price) {
        BigDecimal pr = new BigDecimal(price);
        return taskService.getAllTaskByPrice(pr);
    }
}
