package by.mishastoma.controller;

import by.mishastoma.controller.CrudController;
import by.mishastoma.dto.DTOBook;
import by.mishastoma.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookControllerImpl implements CrudController {
    private final BookService service;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.insert(dtoBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.delete(dtoBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findAll() {
        try {
            List<DTOBook> list = service.findAll();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOBook dtoBook = objectMapper.readValue(obj, DTOBook.class);
            service.update(dtoBook);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
