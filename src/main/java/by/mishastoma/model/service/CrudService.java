package by.mishastoma.model.service;

import java.io.Serializable;

public interface CrudService<M> {
    void insert(M m);

    void delete(Serializable id);

    M findById(Serializable id);

    void update(M m);
}
