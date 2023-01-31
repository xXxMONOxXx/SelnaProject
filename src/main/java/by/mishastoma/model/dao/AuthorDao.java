package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Author;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface AuthorDao extends CrudDao<Author> {
    void insertBookAuthorRelation(long bookId, long authorsId, Connection connection) throws SQLException;

    void deleteBookAuthorRelation(long bookId, long authorId, Connection connection) throws SQLException;

    List<Long> getBooksAuthors(long bookId, Connection connection) throws SQLException;

}
