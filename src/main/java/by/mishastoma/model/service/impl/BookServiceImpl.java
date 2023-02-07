package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.AuthorDao;
import by.mishastoma.model.dao.BookDao;
import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.dto.DTOBook;
import by.mishastoma.model.dto.DTOGenre;
import by.mishastoma.model.entity.AuthorEntity;
import by.mishastoma.model.entity.BookEntity;
import by.mishastoma.model.entity.GenreEntity;
import by.mishastoma.model.service.BookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookServiceImpl implements BookService {

    private final BookDao dao;
    private final ModelMapper modelMapper;

    private BookServiceImpl(BookDao dao, ModelMapper modelMapper){
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public void insert(DTOBook dtoBook) {
        BookEntity book = modelMapper.map(dtoBook, BookEntity.class);
        dao.save(book);
    }

    @Transactional
    @Override
    public void delete(DTOBook dtoBook) {
        BookEntity book = modelMapper.map(dtoBook, BookEntity.class);
        dao.delete(book);
    }

    @Override
    public DTOBook findById(int id) {
        BookEntity bookEntity = dao.findById(id);
        DTOBook book =  modelMapper.map(bookEntity, DTOBook.class);
        return book;
    }

    @Transactional
    @Override
    public void update(DTOBook dtoBook) {
        BookEntity book = modelMapper.map(dtoBook, BookEntity.class);
        dao.update(book);
    }

    @Override
    public DTOBook findBookByIdJpql(Integer id) {
        return modelMapper.map(dao.findBookByIdJpql(id), DTOBook.class);
    }

    @Override
    public DTOBook findBookByIdEntityGraph(Integer id) {
        return modelMapper.map(dao.findBookByIdEntityGraph(id), DTOBook.class);
    }

    @Override
    public DTOBook findBookByIdCriteria(Integer id) {
        return modelMapper.map(dao.findBookByIdCriteria(id), DTOBook.class);
    }

    @Override
    public DTOBook findBookByIsbn(String isbn) {
        return modelMapper.map(dao.findBookByIsbn(isbn), DTOBook.class);
    }
}
