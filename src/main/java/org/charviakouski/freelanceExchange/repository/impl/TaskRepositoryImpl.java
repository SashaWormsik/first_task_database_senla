package org.charviakouski.freelanceExchange.repository.impl;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.charviakouski.freelanceExchange.model.entity.Task;
import org.charviakouski.freelanceExchange.model.entity.Task_;
import org.charviakouski.freelanceExchange.repository.AbstractRepository;
import org.charviakouski.freelanceExchange.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TaskRepositoryImpl extends AbstractRepository<Long, Task> implements TaskRepository {

    @Override
    protected Class<Task> getEntityClass() {
        return Task.class;
    }

    @Override
    public List<Task> getAllTasksByTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = criteriaBuilder.createQuery(getEntityClass());
        Root<Task> root = query.from(getEntityClass());
        query.select(root).where(criteriaBuilder.equal(root.get(Task_.title), title));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Task> getAllTasksByPrice(BigDecimal price) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Task> query = criteriaBuilder.createQuery(getEntityClass());
        Root<Task> root = query.from(getEntityClass());
        query.select(root).where(criteriaBuilder.equal(root.get(Task_.price), price));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Optional<Task> getTaskByIdGraph(Long id) {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("task-entity-graph");
        Map<String, Object> hints = new HashMap<>();
        hints.put("jakarta.persistence.fetch", entityGraph);
        return Optional.ofNullable(entityManager.find(Task.class, id, hints));
    }
}
