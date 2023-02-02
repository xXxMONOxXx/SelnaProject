package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOAuthor;
import by.mishastoma.model.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorControllerImpl implements CrudController {
    private final AuthorService service;
    private final ObjectMapper objectMapper;

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
    public String findAll() {
        try {
            List<DTOAuthor> list = service.findAll();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
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
