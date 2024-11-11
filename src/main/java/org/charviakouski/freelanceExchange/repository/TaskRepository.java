package org.charviakouski.freelanceExchange.repository;

import org.charviakouski.freelanceExchange.model.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAllTasksByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Task> findAllByCustomerId(Long customerId, Pageable pageable);

    @Query("SELECT t FROM Task t " +
            "WHERE t.id IN " +
            "(SELECT ts.id FROM Task ts " +
            "JOIN t.categories c " +
            "WHERE upper(t.title) LIKE upper(concat('%', :title, '%')) " +
            "AND c.name IN :categ_name)")
    Page<Task> findAllByTitleAndCategory(@Param("title") String title, @Param("categ_name") List<String> categoriesName, Pageable pageable);

}
