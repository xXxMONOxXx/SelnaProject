package by.mishastoma.service;

import java.io.Serializable;

public interface CrudService<M> {
    M save(M m);

    void delete(Serializable id);

    M findById(Serializable id);

    void update(M m);
}
