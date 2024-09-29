package org.charviakouski.freelanceExchange.repository.impl;

import lombok.SneakyThrows;
import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskStatusRepositoryImpl extends AbstractRepository<Long, TaskStatus> implements TaskStatusRepository {

    @Override
    protected Class<TaskStatus> getEntityClass() {
        return TaskStatus.class;
    }
}
