package by.mishastoma.service.impl;

import by.mishastoma.exception.BookNotFoundException;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.entity.Author;
import by.mishastoma.model.entity.Book;
import by.mishastoma.model.entity.Genre;
import by.mishastoma.service.BookService;
import by.mishastoma.util.BookSearchRequest;
import by.mishastoma.web.dto.AuthorDto;
import by.mishastoma.web.dto.BookDto;
import by.mishastoma.web.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final ModelMapper modelMapper;
    @Value("${sort.property.book}")
    private String propertySort;

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
        return books.map(mappingContext -> modelMapper.map(mappingContext, BookDto.class));
    }

    @Override
    @Transactional
    public BookDto findBookByIsbn(String isbn) {
        Book bookEntity = bookDao.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
        return modelMapper.map(bookEntity, BookDto.class);
    }

    @Override
    @Transactional
    public Page<BookDto> findBookByGenres(List<GenreDto> genres, int pageNumber, int pageSize) {
        Page<Book> books = bookDao.findByGenres(genres.stream().map(x ->
                modelMapper.map(x, Genre.class)).toList(), pageNumber, pageSize);
        return books.map(mappingContext -> modelMapper.map(mappingContext, BookDto.class));
    }

    @Override
    @Transactional
    public Page<BookDto> findBookByAuthors(List<AuthorDto> authors, int pageNumber, int pageSize) {
        Page<Book> books = bookDao.findByAuthors(authors.stream().map(x ->
                modelMapper.map(x, Author.class)).toList(), pageNumber, pageSize);
        return books.map(mappingContext -> modelMapper.map(mappingContext, BookDto.class));
    }

    @Override
    @Transactional
    public Page<BookDto> findBookByTitle(String title, int pageNumber, int pageSize) {
        Page<Book> books = bookDao.findByTitle(title, pageNumber, pageSize);
        return books.map(mappingContext -> modelMapper.map(mappingContext, BookDto.class));
    }

    @Override
    @Transactional
    public Page<BookDto> findBookWithParameters(BookSearchRequest bookSearchRequest, int pageNumber, int pageSize) {
        List<Author> authors = bookSearchRequest.getAuthors().stream().map(x -> modelMapper.map(x, Author.class)).toList();
        List<Genre> genres = bookSearchRequest.getGenres().stream().map(x -> modelMapper.map(x, Genre.class)).toList();
        Page<Book> books = bookDao.fullSearch(authors, genres, pageNumber, pageSize);
        return books.map(mappingContext -> modelMapper.map(mappingContext, BookDto.class));
    }
}
