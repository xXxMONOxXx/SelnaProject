package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOGenre;
import by.mishastoma.model.service.AuthorService;
import by.mishastoma.model.service.GenreService;
import by.mishastoma.model.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class GenreControllerImpl implements CrudController {
    private final GenreService service;
    private final ObjectMapper objectMapper;

    private GenreControllerImpl(GenreService service, ObjectMapper objectMapper){
        this.service = service;
        this.objectMapper = objectMapper;
    }
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
    public String findById(int id) {
        try {
            DTOGenre genre = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(genre);
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

    public String findGenreByName(String genre){
        try {
            DTOGenre dtoGenre = service.findGenreByName(genre);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoGenre);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
