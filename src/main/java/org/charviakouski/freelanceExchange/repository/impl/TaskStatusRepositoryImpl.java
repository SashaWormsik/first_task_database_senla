package org.charviakouski.freelanceExchange.repository.impl;

import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.TaskStatusRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TaskStatusRepositoryImpl extends AbstractRepository<Long, TaskStatus> implements TaskStatusRepository {

    @Override
    protected Class<TaskStatus> getEntityClass() {
        return TaskStatus.class;
    }
}
