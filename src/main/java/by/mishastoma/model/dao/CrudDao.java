package by.mishastoma.model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface CrudDao<T> {


    void insert(T t, Connection connection) throws SQLException;

    void delete(T t, Connection connection) throws SQLException;

    List<T> findAll(Connection connection) throws SQLException;

    void update(T t, Connection connection) throws SQLException;
}
