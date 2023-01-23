package by.mishastoma.service.impl;

import by.mishastoma.dao.BookDao;
import by.mishastoma.dto.DTOBook;
import by.mishastoma.entity.Book;
import by.mishastoma.exception.DaoException;
import by.mishastoma.exception.ServiceException;
import by.mishastoma.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOBook dtoBook) throws ServiceException {
        Book book = modelMapper.map(dtoBook, Book.class);
        try {
            dao.insert(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(DTOBook dtoBook) throws ServiceException {
        Book book = modelMapper.map(dtoBook, Book.class);
        try {
            dao.delete(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DTOBook> findAll() throws ServiceException {
        try {
            List<Book> books = dao.findAll();
            return books.stream().map(x -> modelMapper.map(x, DTOBook.class)).toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(DTOBook dtoBook) throws ServiceException {
        Book book = modelMapper.map(dtoBook, Book.class);
        try {
            dao.update(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
