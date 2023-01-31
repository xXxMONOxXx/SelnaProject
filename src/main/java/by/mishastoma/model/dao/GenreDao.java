package by.mishastoma.model.dao;

import by.mishastoma.model.entity.Genre;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GenreDao extends CrudDao<Genre> {
    void insertBookGenreRelation(long bookId, long genreId, Connection connection) throws SQLException;

    List<Long> getBooksGenres(long bookId, Connection connection) throws SQLException;

    void deleteBookGenreRelation(long bookId, long genreId, Connection connection) throws SQLException;
}
