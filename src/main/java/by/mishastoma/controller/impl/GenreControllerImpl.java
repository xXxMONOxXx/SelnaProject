package by.mishastoma.controller.impl;

import by.mishastoma.controller.GenreController;
import by.mishastoma.dto.DTOGenre;
import by.mishastoma.exception.ControllerException;
import by.mishastoma.exception.ServiceException;
import by.mishastoma.service.GenreService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GenreControllerImpl implements GenreController {
    private final GenreService service;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) throws ControllerException {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.insert(dtoGenre);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void delete(String obj) throws ControllerException {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.delete(dtoGenre);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public String findAll() throws ControllerException {
        try {
            List<DTOGenre> list = service.findAll();
            return objectMapper.writeValueAsString(list);

        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void update(String obj) throws ControllerException {
        try {
            DTOGenre dtoGenre = objectMapper.readValue(obj, DTOGenre.class);
            service.update(dtoGenre);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }
}
