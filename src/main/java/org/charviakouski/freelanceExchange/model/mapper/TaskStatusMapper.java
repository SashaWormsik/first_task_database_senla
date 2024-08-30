package org.charviakouski.freelanceExchange.model.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.dto.TaskStatusDto;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;

@Data
public class TaskStatusMapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TaskStatusDto fromEntityToDto(TaskStatus taskStatus) {
        TaskStatusDto taskStatusDto = new TaskStatusDto();
        taskStatusDto.setId(taskStatus.getId());
        taskStatusDto.setStatus(taskStatus.getStatus());
        return taskStatusDto;
    }

    public TaskStatus fromDtoToEntity(TaskStatusDto taskStatusDto) {
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setId(taskStatusDto.getId());
        taskStatus.setStatus(taskStatusDto.getStatus());
        return taskStatus;
    }

    @SneakyThrows
    public String fromDtoToJson(TaskStatusDto taskStatusDto) {
        return objectMapper.writeValueAsString(taskStatusDto);
    }

    @SneakyThrows
    public TaskStatusDto fromJsonToDto(String json) {
        return objectMapper.readValue(json, TaskStatusDto.class);
    }
}
