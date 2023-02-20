package by.mishastoma.controller;

import by.mishastoma.model.dto.ItemDto;
import by.mishastoma.model.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemControllerImpl implements CrudController {

    private final ItemService itemService;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            ItemDto itemDto = objectMapper.readValue(obj, ItemDto.class);
            itemService.insert(itemDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        itemService.delete(id);
    }

    @Override
    public String findById(Long id) {
        try {
            ItemDto item = itemService.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(item);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            ItemDto itemDto = objectMapper.readValue(obj, ItemDto.class);
            itemService.update(itemDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
