package by.mishastoma.model.service.impl;

import by.mishastoma.annotation.Transaction;
import by.mishastoma.connection.ConnectionHolder;
import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dao.GenreDao;
import by.mishastoma.model.dao.ItemDao;
import by.mishastoma.model.dto.DTOBook;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Item;
import by.mishastoma.model.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final ItemDao itemDao;
    private final GenreDao genreDao;
    private final ModelMapper modelMapper;
    private final ConnectionHolder connectionHolder;

    @Transaction
    @Override
    public void insert(DTOBook dtoBook) {
        Connection connection = null;
        boolean transactionSuccess = false;
        try {
            connection = connectionHolder.getConnection();
            connectionHolder.beginTransaction(connection);
            Book book = modelMapper.map(dtoBook, Book.class);
            bookDao.insert(book, connection);
            book.setId(bookDao.getIdByIsbn(book.getIsbn(), connection));
            insertBookRelations(book, connection);
            transactionSuccess = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    if (transactionSuccess) {
                        connectionHolder.commitTransaction(connection);
                    } else {
                        connectionHolder.rollbackTransaction(connection);
                    }
                    if (!connectionHolder.releaseConnection(connection)) {
                        throw new RuntimeException("Couldn't release connection, connection closed");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Transaction
    @Override
    public void delete(DTOBook dtoBook) {
        Connection connection = null;
        boolean transactionSuccess = false;
        try {
            connection = connectionHolder.getConnection();
            connectionHolder.beginTransaction(connection);
            Book book = modelMapper.map(dtoBook, Book.class);
            deleteRelations(book, connection);
            bookDao.delete(book, connection);
            transactionSuccess = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    if (transactionSuccess) {
                        connectionHolder.commitTransaction(connection);
                    } else {
                        connectionHolder.rollbackTransaction(connection);
                    }
                    if (!connectionHolder.releaseConnection(connection)) {
                        throw new RuntimeException("Couldn't release connection, connection closed");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public List<DTOBook> findAll() {
        Connection connection = null;
        try {
            connection = connectionHolder.getConnection();
            List<Book> books = bookDao.findAll(connectionHolder.getConnection());
            for (int i = 0; i < books.size(); i++) {
                getDataFromRelations(books.get(i), connection);
            }
            return books.stream().map(x -> modelMapper.map(x, DTOBook.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            if (connection != null) {
                if (!connectionHolder.releaseConnection(connection)) {
                    throw new RuntimeException("Couldn't release connection, connection closed");
                }
            }

        }
    }

    @Transaction
    @Override
    public void update(DTOBook dtoBook) {
        Connection connection = null;
        boolean transactionSuccess = false;
        try {
            connection = connectionHolder.getConnection();
            connectionHolder.beginTransaction(connection);
            Book book = modelMapper.map(dtoBook, Book.class);
            bookDao.update(book, connection);
            updateRelations(book, connection);
            transactionSuccess = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    if (transactionSuccess) {
                        connectionHolder.commitTransaction(connection);
                    } else {
                        connectionHolder.rollbackTransaction(connection);
                    }
                    if (!connectionHolder.releaseConnection(connection)) {
                        throw new RuntimeException("Couldn't release connection, connection closed");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void insertBookRelations(Book book, Connection connection) throws SQLException {
        for (Long id : book.getAuthorIds()) {
            authorDao.insertBookAuthorRelation(book.getId(), id, connection);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.insertBookGenreRelation(book.getId(), id, connection);
        }
        for (int i = 0; i < book.getQuantity(); i++) {
            itemDao.insert(Item.builder().bookId(book.getId()).build(), connection);
        }
    }

    private void deleteRelations(Book book, Connection connection) throws SQLException {
        for (Long id : book.getAuthorIds()) {
            authorDao.deleteBookAuthorRelation(book.getId(), id, connection);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.deleteBookGenreRelation(book.getId(), id, connection);
        }
        List<Long> itemIds = itemDao.getItemsIds(book.getId(), connection);
        for (Long id : itemIds) {
            itemDao.delete(Item.builder().id(id).build(), connection);
        }
    }

    private void updateRelations(Book book, Connection connection) throws SQLException {
        for (Long id : book.getAuthorIds()) {
            authorDao.deleteBookAuthorRelation(book.getId(), id, connection);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.deleteBookGenreRelation(book.getId(), id, connection);
        }
        for (Long id : book.getAuthorIds()) {
            authorDao.insertBookAuthorRelation(book.getId(), id, connection);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.insertBookGenreRelation(book.getId(), id, connection);
        }
        int bookQuantity = book.getQuantity();
        int currentQuantity = itemDao.count(book.getId(), connection);
        if (bookQuantity != currentQuantity) {
            if (bookQuantity > currentQuantity) {
                for (int i = currentQuantity; i < bookQuantity; i++) {
                    itemDao.insert(Item.builder().bookId(book.getId()).build(), connection);
                }
            } else {
                for (int i = currentQuantity; i > bookQuantity; i--) {
                    itemDao.deleteUnsignedItem(book.getId(), connection);
                }
            }
        }
    }

    private void getDataFromRelations(Book book, Connection connection) throws SQLException {
        book.setGenreIds(genreDao.getBooksGenres(book.getId(), connection));
        book.setAuthorIds(authorDao.getBooksAuthors(book.getId(), connection));
        book.setQuantity(itemDao.count(book.getId(), connection));
    }
}
