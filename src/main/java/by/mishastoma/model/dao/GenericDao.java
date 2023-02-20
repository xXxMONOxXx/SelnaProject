package by.mishastoma.model.dao;

import java.io.Serializable;
import java.util.Optional;

public interface GenericDao<T> {
    void save(T entity);

    Optional<T> findById(Serializable id);

    void update(T entity);

    void delete(T entity);
}
