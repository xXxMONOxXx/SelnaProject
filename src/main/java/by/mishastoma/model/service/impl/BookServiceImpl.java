package by.mishastoma.model.service.impl;

import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dto.BookDto;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Service
@Component
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void insert(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookDao.save(book);
    }

    @Transactional
    @Override
    public void delete(Serializable id) {
        Book book = bookDao.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookDao.delete(book);
    }

    @Override
    public BookDto findById(Serializable id) {
        Book bookEntity = bookDao.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Transactional
    @Override
    public void update(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookDao.update(book);
    }

    @Override
    public BookDto findBookByIdJpql(Serializable id) {
        Book bookEntity = bookDao.findByIdJpql(id).orElseThrow(() -> new BookNotFoundException(id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto findBookByIdEntityGraph(Serializable id) {
        Book bookEntity = bookDao.findByIdEntityGraph(id).orElseThrow(() -> new BookNotFoundException(id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto findBookByIdCriteria(Serializable id) {
        Book bookEntity = bookDao.findByIdCriteria(id).orElseThrow(() -> new BookNotFoundException(id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book bookEntity = bookDao.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return modelMapper.map(bookEntity, BookDto.class);
    }
}
