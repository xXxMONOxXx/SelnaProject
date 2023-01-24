package by.mishastoma.controller;

import by.mishastoma.dto.DTOGenre;
import by.mishastoma.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.delete(dtoGenre);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findAll() {
        try {
            List<DTOGenre> list = service.findAll();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.update(dtoGenre);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}