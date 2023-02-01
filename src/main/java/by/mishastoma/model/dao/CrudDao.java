package by.mishastoma.model.dao;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {


    void insert(T t) throws SQLException;

    void delete(T t) throws SQLException;

    List<T> findAll() throws SQLException;

    void update(T t) throws SQLException;
}
