package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Author;

import java.sql.SQLException;
import java.util.List;

public interface AuthorDao extends CrudDao<Author> {
    void insertBookAuthorRelation(long bookId, long authorsId) throws SQLException;

    void deleteBookAuthorRelation(long bookId, long authorId) throws SQLException;

    List<Long> getBooksAuthors(long bookId) throws SQLException;

}
