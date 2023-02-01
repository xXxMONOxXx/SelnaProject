package by.mishastoma.model.service.impl;

import by.mishastoma.annotation.Transaction;
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

    @Transaction
    @Override
    public void insert(DTOBook dtoBook) {
        try {
            Book book = modelMapper.map(dtoBook, Book.class);
            bookDao.insert(book);
            book.setId(bookDao.getIdByIsbn(book.getIsbn()));
            insertBookRelations(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transaction
    @Override
    public void delete(DTOBook dtoBook) {
        try {
            Book book = modelMapper.map(dtoBook, Book.class);
            deleteRelations(book);
            bookDao.delete(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DTOBook> findAll() {
        try {
            List<Book> books = bookDao.findAll();
            for (int i = 0; i < books.size(); i++) {
                getDataFromRelations(books.get(i));
            }
            return books.stream().map(x -> modelMapper.map(x, DTOBook.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transaction
    @Override
    public void update(DTOBook dtoBook) {
        try {
            Book book = modelMapper.map(dtoBook, Book.class);
            bookDao.update(book);
            updateRelations(book);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertBookRelations(Book book) throws SQLException {
        for (Long id : book.getAuthorIds()) {
            authorDao.insertBookAuthorRelation(book.getId(), id);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.insertBookGenreRelation(book.getId(), id);
        }
        for (int i = 0; i < book.getQuantity(); i++) {
            itemDao.insert(Item.builder().bookId(book.getId()).build());
        }
    }

    private void deleteRelations(Book book) throws SQLException {
        for (Long id : book.getAuthorIds()) {
            authorDao.deleteBookAuthorRelation(book.getId(), id);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.deleteBookGenreRelation(book.getId(), id);
        }
        List<Long> itemIds = itemDao.getItemsIds(book.getId());
        for (Long id : itemIds) {
            itemDao.delete(Item.builder().id(id).build());
        }
    }

    private void updateRelations(Book book) throws SQLException {
        for (Long id : book.getAuthorIds()) {
            authorDao.deleteBookAuthorRelation(book.getId(), id);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.deleteBookGenreRelation(book.getId(), id);
        }
        for (Long id : book.getAuthorIds()) {
            authorDao.insertBookAuthorRelation(book.getId(), id);
        }
        for (Long id : book.getGenreIds()) {
            genreDao.insertBookGenreRelation(book.getId(), id);
        }
        int bookQuantity = book.getQuantity();
        int currentQuantity = itemDao.count(book.getId());
        if (bookQuantity != currentQuantity) {
            if (bookQuantity > currentQuantity) {
                for (int i = currentQuantity; i < bookQuantity; i++) {
                    itemDao.insert(Item.builder().bookId(book.getId()).build());
                }
            } else {
                for (int i = currentQuantity; i > bookQuantity; i--) {
                    itemDao.deleteUnsignedItem(book.getId());
                }
            }
        }
    }

    private void getDataFromRelations(Book book) throws SQLException {
        book.setGenreIds(genreDao.getBooksGenres(book.getId()));
        book.setAuthorIds(authorDao.getBooksAuthors(book.getId()));
        book.setQuantity(itemDao.count(book.getId()));
    }
}
