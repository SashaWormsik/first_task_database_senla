package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Long> {
}
