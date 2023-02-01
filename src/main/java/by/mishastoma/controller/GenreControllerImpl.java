package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOGenre;
import by.mishastoma.model.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreControllerImpl implements CrudController {
    private final GenreService service;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.insert(dtoGenre);
        } catch (JsonProcessingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.delete(dtoGenre);
        } catch (JsonProcessingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findAll() {
        try {
            List<DTOGenre> list = service.findAll();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.update(dtoGenre);
        } catch (JsonProcessingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
