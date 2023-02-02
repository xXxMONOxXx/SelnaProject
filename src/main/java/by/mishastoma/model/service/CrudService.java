package by.mishastoma.model.service;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<M> {
    void insert(M m);

    void delete(M m);

    List<M> findAll();

    void update(M m);
}
