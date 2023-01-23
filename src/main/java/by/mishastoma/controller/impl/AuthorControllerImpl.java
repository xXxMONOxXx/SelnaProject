package by.mishastoma.controller.impl;

import by.mishastoma.controller.AuthorController;
import by.mishastoma.dto.DTOAuthor;
import by.mishastoma.exception.ControllerException;
import by.mishastoma.exception.ServiceException;
import by.mishastoma.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthorControllerImpl implements AuthorController {
    private final AuthorService service;
    private final ObjectMapper objectMapper;


    @Override
    public void insert(String obj) throws ControllerException {
        try {
            DTOAuthor dtoAuthor = objectMapper.readValue(obj, DTOAuthor.class);
            service.insert(dtoAuthor);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void delete(String obj) throws ControllerException {
        try {
            DTOAuthor dtoAuthor = objectMapper.readValue(obj, DTOAuthor.class);
            service.delete(dtoAuthor);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public String findAll() throws ControllerException {
        try {
            List<DTOAuthor> list = service.findAll();
            return objectMapper.writeValueAsString(list);

        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void update(String obj) throws ControllerException {
        try {
            DTOAuthor dtoAuthor = objectMapper.readValue(obj, DTOAuthor.class);
            service.update(dtoAuthor);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }
}
