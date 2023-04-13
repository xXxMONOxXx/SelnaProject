package by.mishastoma.web.controller.impl;

import by.mishastoma.service.ItemService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.ItemDto;
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

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemControllerImpl implements CrudController<ItemDto> {

    private final ItemService itemService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid ItemDto item) {
        itemService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("Item was created");
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Item was deleted");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> findById(@PathVariable Long id) {
        ItemDto item = itemService.findById(id);
        return ResponseEntity.ok(item);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid ItemDto item, @PathVariable Long id) {
        item.setId(id);
        itemService.update(item);
        return ResponseEntity.status(HttpStatus.OK).body("Item was updated");
    }
}
