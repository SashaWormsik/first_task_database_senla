package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.springframework.stereotype.Component;

@Component
public class TaskRepositoryImpl extends AbstractRepository<Long, Task> implements TaskRepository {

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }
}
