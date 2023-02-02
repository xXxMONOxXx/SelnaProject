package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Book;

import java.sql.SQLException;

public interface BookDao extends CrudDao<Book> {
    Book get(long id) throws SQLException;

    Long getIdByIsbn(String isbn) throws SQLException;
}
