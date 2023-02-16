package by.mishastoma.controller;

import by.mishastoma.model.dto.GenreDto;
import by.mishastoma.model.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreControllerImpl implements CrudController {
    private final GenreService service;
    private final ObjectMapper objectMapper;

    private GenreControllerImpl(GenreService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void insert(String obj) {
        try {
            GenreDto genreDto = objectMapper.readValue(obj, GenreDto.class);
            service.insert(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public String findById(Long id) {
        try {
            GenreDto genre = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(genre);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            GenreDto genreDto = objectMapper.readValue(obj, GenreDto.class);
            service.update(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findGenreByName(String genre) {
        try {
            GenreDto genreDto = service.findGenreByName(genre);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
