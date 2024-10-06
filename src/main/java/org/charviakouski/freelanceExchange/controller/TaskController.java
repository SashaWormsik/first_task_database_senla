package org.charviakouski.freelanceExchange.controller;

import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.mapper.EntityMapper;
import org.charviakouski.freelanceExchange.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getById(@PathVariable(name = "id") long id) {
        return taskService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto insert(@RequestBody TaskDto taskDto) {
        return taskService.insert(taskDto);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto update(@PathVariable(name = "id") long id, @RequestBody TaskDto taskDto) {
        taskDto.setId(id);
        return taskService.update(taskDto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") long id) {
        taskService.delete(id);
    }

    @GetMapping(value = "/title")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTaskByTitle(@RequestParam(name = "title") String title) {
        return taskService.getAllTaskByTitle(title);
    }

    @GetMapping(value = "/price")
    @ResponseStatus(HttpStatus.OK)
    public List<TaskDto> getAllTaskByPrice(@RequestParam(name = "price") String price) {
        BigDecimal pr = new BigDecimal(price);
        return taskService.getAllTaskByPrice(pr);
    }
}
