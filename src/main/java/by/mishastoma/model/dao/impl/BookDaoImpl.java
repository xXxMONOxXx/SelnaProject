package by.mishastoma.model.dao.impl;

import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private static final String SELECT_BOOK_ID_BY_ISBN = "select id " +
            "from books " +
            "where isbn = ?";

    private static final String INSERT_QUERY = "insert into books " +
            "(title, isbn, release_date) values" +
            "(?,?,?)";
    private static final String UPDATE_QUERY = "update books " +
            "set title = ?, " +
            "isbn = ?, " +
            "release_date = ? " +
            "where id = ? ";

    private static final String DELETE_QUERY = "delete from books " +
            "where id = ?";
    private static final String SELECT_ALL_QUERY = "select * " +
            "from books";

    private static final String SELECT_BY_ID_QUERY = "select * " +
            "from books " +
            "where id = ?";

    private static final String ID = "id";
    private static final String ISBN = "isbn";
    private static final String TITLE = "title";
    private static final String RELEASE_DATE = "release_date";
    private final ConnectionHolder connectionHolder;

    @Override
    public void insert(Book t) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
            statement.setString(1, t.getTitle());
            statement.setString(2, t.getIsbn());
            statement.setDate(3, Date.valueOf(t.getReleaseDate()));
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connectionHolder.releaseConnection(connection);
        }
    }

    @Override
    public void delete(Book t) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(DELETE_QUERY)) {
            statement.setLong(1, t.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connectionHolder.releaseConnection(connection);
        }
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = Book.builder().
                        id(resultSet.getLong(ID)).
                        isbn(resultSet.getString(ISBN)).
                        title(resultSet.getString(TITLE)).
                        releaseDate(resultSet.getDate(RELEASE_DATE).toLocalDate()).
                        build();
                books.add(book);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connectionHolder.releaseConnection(connection);
        }
        return books;
    }

    @Override
    public void update(Book t) throws SQLException {
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY)) {
            statement.setString(1, t.getTitle());
            statement.setString(2, t.getIsbn());
            statement.setDate(3, Date.valueOf(t.getReleaseDate()));
            statement.setLong(4, t.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connectionHolder.releaseConnection(connection);
        }
    }

    @Override
    public Book get(long id) throws SQLException {
        Book book = null;
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            book = Book.builder().
                    id(resultSet.getLong(ID)).
                    isbn(resultSet.getString(ISBN)).
                    title(resultSet.getString(TITLE)).
                    releaseDate(resultSet.getDate(RELEASE_DATE).toLocalDate()).
                    build();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connectionHolder.releaseConnection(connection);
        }
        return book;
    }

    @Override
    public Long getIdByIsbn(String isbn) throws SQLException {
        Long id = null;
        Connection connection = connectionHolder.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_ID_BY_ISBN)) {
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(ID);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        finally {
            connectionHolder.releaseConnection(connection);
        }
        return id;
    }
}
