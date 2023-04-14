package by.mishastoma.service;

import org.springframework.data.domain.Page;

import java.io.Serializable;

public interface CrudService<M> {
    M save(M m);

    void delete(Serializable id);

    M findById(Serializable id);

    void update(M m);

    Page<M> getAll(int pageNumber, int pageSize);
}
