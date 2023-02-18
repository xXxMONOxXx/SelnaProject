package by.mishastoma.controller;

import by.mishastoma.model.dto.BookDto;
import by.mishastoma.model.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookControllerImpl implements CrudController {
    private final BookService bookService;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            BookDto bookDto = objectMapper.readValue(obj, BookDto.class);
            bookService.insert(bookDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }

    @Override
    public String findById(Long id) {
        try {
            BookDto book = bookService.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            BookDto bookDto = objectMapper.readValue(obj, BookDto.class);
            bookService.update(bookDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdJpql(Long id) {
        try {
            BookDto book = bookService.findBookByIdJpql(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdEntityGraph(Long id) {
        try {
            BookDto book = bookService.findBookByIdEntityGraph(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIdCriteria(Long id) {
        try {
            BookDto book = bookService.findBookByIdCriteria(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findBookByIsbn(String isbn) {
        try {
            BookDto book = bookService.findBookByIsbn(isbn);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(book);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
