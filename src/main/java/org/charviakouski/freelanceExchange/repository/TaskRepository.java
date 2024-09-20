package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;

import java.math.BigDecimal;
import java.util.List;

public interface TaskRepository extends DefaultRepository<Long, Task> {
    List<Task> getAllTasksByTitle(String title);

    List<Task> getAllTasksByPrice(BigDecimal price);
}
