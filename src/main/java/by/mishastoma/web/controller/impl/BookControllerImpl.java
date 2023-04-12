package by.mishastoma.web.controller.impl;

import by.mishastoma.service.BookService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookControllerImpl implements CrudController<BookDto> {
    private final BookService bookService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid BookDto book) {
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/{id}")
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
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid BookDto book, @PathVariable Long id) {
        book.setId(id);
        bookService.update(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<BookDto> findBookByIsbn(@RequestParam @Size(max = 13, min = 13,
            message = "Isbn must be 13 numbers long") String isbn) {
        BookDto book = bookService.findBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }
}
