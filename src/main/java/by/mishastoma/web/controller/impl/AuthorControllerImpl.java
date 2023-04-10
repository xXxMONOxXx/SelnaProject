package by.mishastoma.web.controller.impl;

import by.mishastoma.service.AuthorService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.AuthorDto;
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
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.ok().build();
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
        return ResponseEntity.ok().build();
    }
}