package by.mishastoma.model.dao.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.entity.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private static final String INSERT_BOOK_GENRE_RELATION_QUERY = "insert into book_genres " +
            "(fk_book_id, fk_genre_id) values " +
            "(?,?)";
    private static final String DELETE_BOOK_GENRE_RELATION_QUERY = "delete from book_genres " +
            "where fk_book_id = ? and fk_genre_id = ?";

    private static final String SELECT_RELATION_IDS_BOOK_GENRES_QUERY = "select fk_genre_id " +
            "from book_genres " +
            "where fk_book_id = ?";

    private static final String ID = "id";
    private static final String GENRE_ID = "fk_genre_id";
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(Genre t) {

    }

    @Override
    public void delete(Genre t) {

    }

    @Override
    public List<Genre> findAll() {
        return null;
    }

    @Override
    public void update(Genre t) {

    }

    @Override
    public void insertBookGenreRelation(long bookId, long genreId) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_GENRE_RELATION_QUERY)) {
            statement.setLong(1, bookId);
            statement.setLong(2, genreId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connectionHolder.releaseConnection(connection);
        }
    }

    @Override
    public List<Long> getBooksGenres(long bookId) throws SQLException {
        List<Long> ids = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_RELATION_IDS_BOOK_GENRES_QUERY)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getLong(GENRE_ID));
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connectionHolder.releaseConnection(connection);
        }
        return ids;
    }

    @Override
    public void deleteBookGenreRelation(long bookId, long genreId) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_GENRE_RELATION_QUERY)) {
            statement.setLong(1, bookId);
            statement.setLong(2, genreId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        } finally {
            connectionHolder.releaseConnection(connection);
        }
    }
}
