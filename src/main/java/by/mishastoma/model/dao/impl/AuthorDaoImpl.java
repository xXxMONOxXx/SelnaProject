package by.mishastoma.model.dao.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.entity.Author;
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
public class AuthorDaoImpl implements AuthorDao {

    private static final String INSERT_BOOK_AUTHOR_RELATION_QUERY = "insert into books_author " +
            "(fk_book_id, fk_author_id) values " +
            "(?,?)";
    private static final String DELETE_BOOK_AUTHOR_RELATION_QUERY = "delete from books_author " +
            "where fk_book_id = ? and fk_author_id = ?";

    private static final String SELECT_RELATION_IDS_BOOKS_AUTHORS_QUERY = "select fk_author_id " +
            "from books_author " +
            "where fk_book_id = ?";

    private static final String ID = "id";
    private static final String AUTHOR_ID = "fk_author_id";
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(Author t) {

    }

    @Override
    public void delete(Author t) {

    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public void update(Author t) {

    }

    @Override
    public void insertBookAuthorRelation(long bookId, long authorsId) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_AUTHOR_RELATION_QUERY)) {
            statement.setLong(1, bookId);
            statement.setLong(2, authorsId);
            statement.executeUpdate();
            connectionHolder.releaseConnection(connection);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void deleteBookAuthorRelation(long bookId, long authorId) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOK_AUTHOR_RELATION_QUERY)) {
            statement.setLong(1, bookId);
            statement.setLong(2, authorId);
            statement.executeUpdate();
            connectionHolder.releaseConnection(connection);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Long> getBooksAuthors(long bookId) throws SQLException {
        List<Long> ids = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_RELATION_IDS_BOOKS_AUTHORS_QUERY)) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ids.add(resultSet.getLong(AUTHOR_ID));
            }
            connectionHolder.releaseConnection(connection);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return ids;
    }
}
