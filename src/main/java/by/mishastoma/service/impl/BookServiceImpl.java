package by.mishastoma.service.impl;

import by.mishastoma.dao.BookDao;
import by.mishastoma.dto.DTOBook;
import by.mishastoma.entity.Book;
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
    public void insert(DTOBook dtoBook) {
        Book book = modelMapper.map(dtoBook, Book.class);
        dao.insert(book);
    }

    @Override
    public void delete(DTOBook dtoBook) {
        Book book = modelMapper.map(dtoBook, Book.class);
        dao.delete(book);
    }

    @Override
    public List<DTOBook> findAll() {
        List<Book> books = dao.findAll();
        return books.stream().map(x -> modelMapper.map(x, DTOBook.class)).toList();
    }

    @Override
    public void update(DTOBook dtoBook) {
        Book book = modelMapper.map(dtoBook, Book.class);
        dao.update(book);
    }
}
