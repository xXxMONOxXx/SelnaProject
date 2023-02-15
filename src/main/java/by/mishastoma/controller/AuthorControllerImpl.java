package by.mishastoma.controller;

import by.mishastoma.model.dto.AuthorDto;
import by.mishastoma.model.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorControllerImpl implements CrudController {
    private final AuthorService service;
    private final ObjectMapper objectMapper;

    private AuthorControllerImpl(AuthorService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void insert(String obj) {
        try {
            AuthorDto authorDto = objectMapper.readValue(obj, AuthorDto.class);
            service.insert(authorDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            AuthorDto authorDto = objectMapper.readValue(obj, AuthorDto.class);
            service.delete(authorDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findById(long id) {
        try {
            AuthorDto author = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(author);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            AuthorDto authorDto = objectMapper.readValue(obj, AuthorDto.class);
            service.update(authorDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
