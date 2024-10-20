package org.charviakouski.freelanceExchange.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractRepository<K, T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected abstract Class<T> getEntityClass();

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Optional<T> getById(K id) {
        return Optional.ofNullable(entityManager.find(getEntityClass(), id));
    }

    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(getEntityClass());
        Root<T> root = query.from(getEntityClass());
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public boolean delete(K id) {
        getById(id).ifPresent(entityManager::remove);
        return getById(id).isEmpty();
    }

}
