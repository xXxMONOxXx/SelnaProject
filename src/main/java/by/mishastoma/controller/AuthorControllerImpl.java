package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.service.AuthorService;
import by.mishastoma.model.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class AuthorControllerImpl implements CrudController {
    private final AuthorService service;
    private final ObjectMapper objectMapper;

    private AuthorControllerImpl(AuthorService service, ObjectMapper objectMapper){
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void insert(String obj) {
        try {
            DTOAuthor dtoAuthor = objectMapper.readValue(obj, DTOAuthor.class);
            service.insert(dtoAuthor);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOAuthor dtoAuthor = objectMapper.readValue(obj, DTOAuthor.class);
            service.delete(dtoAuthor);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findById(int id) {
        try {
            DTOAuthor author = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(author);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOAuthor dtoAuthor = objectMapper.readValue(obj, DTOAuthor.class);
            service.update(dtoAuthor);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
