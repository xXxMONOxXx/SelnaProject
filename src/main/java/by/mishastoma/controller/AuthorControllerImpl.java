package by.mishastoma.controller;

import by.mishastoma.model.dto.AuthorDto;
import by.mishastoma.model.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorControllerImpl implements CrudController<AuthorDto> {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String author) {
        try {
            AuthorDto authorDto = objectMapper.readValue(author, AuthorDto.class);
            authorService.insert(authorDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        authorService.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        AuthorDto author = authorService.findById(id);
        return ResponseEntity.ok(author);
    }

    @Override
    public void update(String obj) {
        try {
            AuthorDto authorDto = objectMapper.readValue(obj, AuthorDto.class);
            authorService.update(authorDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
