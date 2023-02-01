package by.mishastoma.model.service;

import java.sql.SQLException;
import java.util.List;

public interface CrudService<M> {
    void insert(M m) throws SQLException;

    void delete(M m) throws SQLException;

    List<M> findAll() throws SQLException;

    void update(M m) throws SQLException;
}
