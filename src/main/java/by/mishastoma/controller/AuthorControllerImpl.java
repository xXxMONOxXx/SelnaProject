package by.mishastoma.controller;

import by.mishastoma.model.dto.AuthorDto;
import by.mishastoma.model.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorControllerImpl implements CrudController {
    private final AuthorService authorService;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            AuthorDto authorDto = objectMapper.readValue(obj, AuthorDto.class);
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
    public String findById(Long id) {
        try {
            AuthorDto author = authorService.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(author);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
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
