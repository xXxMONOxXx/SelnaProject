package by.mishastoma.controller;

import by.mishastoma.model.dto.GenreDto;
import by.mishastoma.model.dto.ItemDto;
import by.mishastoma.model.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemControllerImpl implements CrudController<ItemDto> {

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
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        ItemDto item = itemService.findById(id);
        return ResponseEntity.ok(item);
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
