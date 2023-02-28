package by.mishastoma.controller;

import by.mishastoma.model.dto.BookDto;
import by.mishastoma.model.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookControllerImpl implements CrudController<BookDto> {
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
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok(book);
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
