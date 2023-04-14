package by.mishastoma.web.controller.impl;

import by.mishastoma.service.AuthorService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.AuthorDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorControllerImpl implements CrudController<AuthorDto> {
    private final AuthorService authorService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody AuthorDto author) {
        authorService.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body("Author was created");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Author was deleted");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        AuthorDto author = authorService.findById(id);
        return ResponseEntity.ok(author);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid AuthorDto author, @PathVariable Long id) {
        author.setId(id);
        authorService.update(author);
        return ResponseEntity.status(HttpStatus.OK).body("Author was updated");
    }

    @Override
    @GetMapping("/browse")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                    @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<AuthorDto> authors = authorService.getAll(pageNumber, pageSize);
        return ResponseEntity.ok(authors);
    }
}
