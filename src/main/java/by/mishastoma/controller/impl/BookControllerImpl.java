package by.mishastoma.controller.impl;

import by.mishastoma.controller.BookController;
import by.mishastoma.dto.DTOBook;
import by.mishastoma.exception.ControllerException;
import by.mishastoma.exception.ServiceException;
import by.mishastoma.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {
    private final BookService service;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) throws ControllerException {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.insert(dtoBook);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void delete(String obj) throws ControllerException {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.delete(dtoBook);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public String findAll() throws ControllerException {
        try {
            List<DTOBook> list = service.findAll();
            return objectMapper.writeValueAsString(list);

        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void update(String obj) throws ControllerException {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.update(dtoBook);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }
}
