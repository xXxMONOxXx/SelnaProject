package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOBook;
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
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.insert(dtoBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.delete(dtoBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findById(int id) {
        try {
            DTOBook book = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.update(dtoBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdJpql(Integer id) {
        try {
            DTOBook book = service.findBookByIdJpql(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdEntityGraph(Integer id) {
        try {
            DTOBook book = service.findBookByIdEntityGraph(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdCriteria(Integer id) {
        try {
            DTOBook book = service.findBookByIdCriteria(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIsbn(String isbn) {
        try {
            DTOBook book = service.findBookByIsbn(isbn);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
