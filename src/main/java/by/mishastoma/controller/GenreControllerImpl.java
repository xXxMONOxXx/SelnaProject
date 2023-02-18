package by.mishastoma.controller;

import by.mishastoma.model.dto.GenreDto;
import by.mishastoma.model.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreControllerImpl implements CrudController {
    private final GenreService genreService;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            GenreDto genreDto = objectMapper.readValue(obj, GenreDto.class);
            genreService.insert(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        genreService.delete(id);
    }

    @Override
    public String findById(Long id) {
        try {
            GenreDto genre = genreService.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(genre);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            GenreDto genreDto = objectMapper.readValue(obj, GenreDto.class);
            genreService.update(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findGenreByName(String name) {
        try {
            GenreDto genreDto = genreService.findGenreByName(name);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(genreDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
