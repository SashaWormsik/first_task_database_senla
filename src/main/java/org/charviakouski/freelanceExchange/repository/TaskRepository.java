package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Long, Task> {
    List<Task> getAllTasksByTitle(String title);

    List<Task> getAllTasksByPrice(BigDecimal price);

    Optional<Task> getTaskByIdGraph(Long id);
}
