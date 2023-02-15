package by.mishastoma.controller;

import by.mishastoma.model.dto.BookDto;
import by.mishastoma.model.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class BookControllerImpl implements CrudController {
    private final BookService service;
    private final ObjectMapper objectMapper;

    private BookControllerImpl(BookService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void insert(String obj) {
        try {
            BookDto bookDto = objectMapper.readValue(obj, BookDto.class);
            service.insert(bookDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            BookDto bookDto = objectMapper.readValue(obj, BookDto.class);
            service.delete(bookDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findById(long id) {
        try {
            BookDto book = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            BookDto bookDto = objectMapper.readValue(obj, BookDto.class);
            service.update(bookDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdJpql(long id) {
        try {
            BookDto book = service.findBookByIdJpql(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdEntityGraph(long id) {
        try {
            BookDto book = service.findBookByIdEntityGraph(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdCriteria(long id) {
        try {
            BookDto book = service.findBookByIdCriteria(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIsbn(String isbn) {
        try {
            BookDto book = service.findBookByIsbn(isbn);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
