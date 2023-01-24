package by.mishastoma.service;

import java.util.List;

public interface CrudService<M> {
    void insert(M m);

    void delete(M m);

    List<M> findAll();

    void update(M m);
}
