package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOItem;
import by.mishastoma.model.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemControllerImpl implements CrudController {

    private final ItemService service;
    private final ObjectMapper objectMapper;

    private ItemControllerImpl(ItemService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

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
    public String findById(int id) {
        try {
            DTOItem item = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
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
