package by.mishastoma.model.service;

import java.io.Serializable;

public interface CrudService<M> {
    void insert(M m);

    void delete(M m);

    M findById(Serializable id);

    void update(M m);
}
