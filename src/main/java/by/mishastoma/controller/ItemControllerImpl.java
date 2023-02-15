package by.mishastoma.controller;

import by.mishastoma.model.dto.ItemDto;
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
            ItemDto itemDto = objectMapper.readValue(obj, ItemDto.class);
            service.insert(itemDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            ItemDto itemDto = objectMapper.readValue(obj, ItemDto.class);
            service.delete(itemDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findById(long id) {
        try {
            ItemDto item = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            ItemDto itemDto = objectMapper.readValue(obj, ItemDto.class);
            service.update(itemDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
