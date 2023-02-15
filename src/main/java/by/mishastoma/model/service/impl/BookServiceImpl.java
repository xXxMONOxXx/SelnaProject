package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dto.BookDto;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;

@Component
public class BookServiceImpl implements BookService {

    private final BookDao dao;
    private final ModelMapper modelMapper;

    private BookServiceImpl(BookDao dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void insert(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        dao.save(book);
    }

    @Transactional
    @Override
    public void delete(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        dao.delete(book);
    }

    @Override
    public BookDto findById(Serializable id) {
        Book bookEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find book with id +" + id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Transactional
    @Override
    public void update(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        dao.update(book);
    }

    @Override
    public BookDto findBookByIdJpql(Serializable id) {
        Book bookEntity = dao.findByIdJpql(id).orElseThrow(() -> new EntityNotFoundException("Can't find book with id " + id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto findBookByIdEntityGraph(Serializable id) {
        Book bookEntity = dao.findByIdEntityGraph(id).orElseThrow(() -> new EntityNotFoundException("Can't find book with id " + id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto findBookByIdCriteria(Serializable id) {
        Book bookEntity = dao.findByIdCriteria(id).orElseThrow(() -> new EntityNotFoundException("Can't find book with id " + id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book bookEntity = dao.findByIsbn(isbn).orElseThrow(() -> new EntityNotFoundException("Can't find book with isbn " + isbn));
        return modelMapper.map(bookEntity, BookDto.class);
    }
}
