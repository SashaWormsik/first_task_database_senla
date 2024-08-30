package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.TaskDto;
import org.charviakouski.freelanceExchange.model.entity.Task;

import java.util.stream.Collectors;

@Data
public class TaskMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private UserInfoMapper userInfoMapper = new UserInfoMapper();
    private TaskStatusMapper taskStatusMapper = new TaskStatusMapper();
    private CategoryMapper categoryMapper = new CategoryMapper();

    public TaskDto fromEntityToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setPrice(task.getPrice());
        taskDto.setDeadline(task.getDeadline());
        taskDto.setCreateDate(task.getCreateDate());
        taskDto.setCustomerId(userInfoMapper.fromEntityToDto(task.getCustomerId()));
        taskDto.setStatusId(taskStatusMapper.fromEntityToDto(task.getStatusId()));
        taskDto.setCategories(task.getCategories().stream()
                .map(category -> categoryMapper.fromEntityToDto(category))
                .collect(Collectors.toList()));
        return taskDto;
    }

    public Task fromDtoToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setPrice(taskDto.getPrice());
        task.setDeadline(taskDto.getDeadline());
        task.setCreateDate(taskDto.getCreateDate());
        task.setCustomerId(userInfoMapper.fromDtoToEntity(taskDto.getCustomerId()));
        task.setStatusId(taskStatusMapper.fromDtoToEntity(taskDto.getStatusId()));
        task.setCategories(taskDto.getCategories().stream()
                .map(categoryDto -> categoryMapper.fromDtoToEntity(categoryDto))
                .collect(Collectors.toList()));
        return task;
    }

    @SneakyThrows
    public String fromDtoTOJson(TaskDto taskDto) {
        return objectMapper.writeValueAsString(taskDto);
    }

    @SneakyThrows
    public TaskDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, TaskDto.class);
    }
}
