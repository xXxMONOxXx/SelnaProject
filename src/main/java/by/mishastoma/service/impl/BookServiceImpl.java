package by.mishastoma.service.impl;

import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Book;
import by.mishastoma.service.BookService;
import by.mishastoma.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookDto save(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        return modelMapper.map(bookDao.save(book), BookDto.class);
    }

    @Override
    @Transactional
    public void delete(Serializable id) {
        Book book = bookDao.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        bookDao.delete(book);
    }

    @Override
    @Transactional
    public BookDto findById(Serializable id) {
        Book bookEntity = bookDao.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    @Transactional
    public void update(BookDto bookDto) {
        Book book = modelMapper.map(bookDto, Book.class);
        bookDao.update(book);
    }

    @Override
    @Transactional
    public Page<BookDto> getAll(int pageNumber, int pageSize) {
        Page<Book> books = bookDao.getAll(pageNumber, pageSize);
        List<BookDto> bookDtos = new ArrayList<>();
        for (Book book : books.getContent()) {
            bookDtos.add(modelMapper.map(book, BookDto.class));
        }
        return new PageImpl<>(bookDtos, books.getPageable(), books.getTotalElements());
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
    @Transactional
    public BookDto findBookByIsbn(String isbn) {
        Book bookEntity = bookDao.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return modelMapper.map(bookEntity, BookDto.class);
    }
}
