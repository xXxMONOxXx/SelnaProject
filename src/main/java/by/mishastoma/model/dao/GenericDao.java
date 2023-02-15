package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Author;

import java.io.Serializable;
import java.util.Optional;

public interface GenericDao<T> {
    void save(T entity);

    Optional<T> findById(Serializable id);

    void update(T entity);

    void delete(T entity);
}
