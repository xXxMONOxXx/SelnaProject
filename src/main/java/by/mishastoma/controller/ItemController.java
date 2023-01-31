package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOItem;
import by.mishastoma.model.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ItemController implements CrudController {

    private final ItemService service;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            DTOItem dtoItem = objectMapper.readValue(obj, DTOItem.class);
            service.insert(dtoItem);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOItem dtoItem = objectMapper.readValue(obj, DTOItem.class);
            service.delete(dtoItem);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findAll() {
        try {
            List<DTOItem> dtoItems = service.findAll();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dtoItems);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOItem dtoItem = objectMapper.readValue(obj, DTOItem.class);
            service.update(dtoItem);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}