package by.mishastoma.web.controller.impl;

import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.BookDto;
import by.mishastoma.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookControllerImpl implements CrudController<BookDto> {
    private final BookService bookService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody BookDto book) {
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        BookDto book = bookService.findById(id);
        return ResponseEntity.ok(book);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody  BookDto book) {
        bookService.update(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDto> findBookByIsbn(@PathVariable String isbn) {
        BookDto book = bookService.findBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }
}
