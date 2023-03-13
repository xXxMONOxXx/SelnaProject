package by.mishastoma.web.controller.impl;

import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.ItemDto;
import by.mishastoma.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemControllerImpl implements CrudController<ItemDto> {

    private final ItemService itemService;

    @Override
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody ItemDto item) {
        itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        ItemDto item = itemService.findById(id);
        return ResponseEntity.ok(item);
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ItemDto item) {
        itemService.update(item);
        return ResponseEntity.ok().build();
    }
}
