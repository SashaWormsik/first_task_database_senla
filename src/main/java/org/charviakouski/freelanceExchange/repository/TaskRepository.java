package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllTasksByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Task> findAllTasksByPrice(BigDecimal price, Pageable pageable);

}
