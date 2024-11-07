package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> getAllTasksByTitle(String title);

    List<Task> getAllTasksByPrice(BigDecimal price);

}
