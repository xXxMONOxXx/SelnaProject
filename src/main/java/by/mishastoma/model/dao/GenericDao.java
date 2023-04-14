package by.mishastoma.model.dao;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Optional;

public interface GenericDao<T> {
    T save(T entity);

    Optional<T> findById(Serializable id);

    void update(T entity);

    void delete(T entity);

    Page<T> getAll(int pageNumber, int pageSize);
}
