package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Book;

import java.sql.Connection;
import java.sql.SQLException;

public interface BookDao extends CrudDao<Book> {
    Book get(long id, Connection connection) throws SQLException;

    Long getIdByIsbn(String isbn, Connection connection) throws SQLException;
}
