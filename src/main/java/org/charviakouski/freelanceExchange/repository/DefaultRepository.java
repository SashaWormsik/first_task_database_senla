package org.charviakouski.freelanceExchange.repository;

import java.util.List;
import java.util.Optional;

public interface DefaultRepository <K, T>{

    T create(T entity);
    Optional<T> getById(K id);
    List<T> getAll();
    T update(T entity);
    void delete(K id);
}
