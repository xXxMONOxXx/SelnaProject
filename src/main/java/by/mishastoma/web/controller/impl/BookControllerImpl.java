package by.mishastoma.web.controller.impl;

import by.mishastoma.service.BookService;
import by.mishastoma.util.BookSearchRequest;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.AuthorDto;
import by.mishastoma.web.dto.BookDto;
import by.mishastoma.web.dto.GenreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookControllerImpl implements CrudController<BookDto> {
    private final BookService bookService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid BookDto book) {
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book was created");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Book was deleted");
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
        return ResponseEntity.status(HttpStatus.OK).body("Book was updated");
    }

    @Override
    @GetMapping("/browse")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                    @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<BookDto> authors = bookService.getAll(pageNumber, pageSize);
        return ResponseEntity.ok(authors);
    }

    @GetMapping
    public ResponseEntity<BookDto> findBookByIsbn
            (@RequestParam @Pattern(regexp = "\\d{10}|\\d{13}",
                    message = "ISBN must be 10 or 13 characters long and contain only numbers") String isbn) {
        BookDto book = bookService.findBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/search/authors")
    public ResponseEntity<?> searchByAuthors(@RequestBody List<AuthorDto> authors,
                                             @RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                             @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<BookDto> books = bookService.findBookByAuthors(authors, pageNumber, pageSize);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/genres")
    public ResponseEntity<?> searchByGenres(@RequestBody List<GenreDto> genres,
                                            @RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<BookDto> books = bookService.findBookByGenres(genres, pageNumber, pageSize);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search/title")
    public ResponseEntity<?> searchByTitle(@Size(max = 256) @RequestParam(name = "title") String title,
                                           @RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                           @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<BookDto> books = bookService.findBookByTitle(title, pageNumber, pageSize);
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestBody BookSearchRequest bookSearchRequest,
                                    @RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                    @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<BookDto> books = bookService.findBookWithParameters(bookSearchRequest, pageNumber, pageSize);
        return ResponseEntity.ok().body(books);
    }
}
