package by.mishastoma.web.controller.impl;

import by.mishastoma.service.ItemService;
import by.mishastoma.service.UserService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.ItemDto;
import by.mishastoma.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemControllerImpl implements CrudController<ItemDto> {
    private final UserService userService;
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

    @Override
    @GetMapping("/browse")
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                    @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<ItemDto> authors = itemService.getAll(pageNumber, pageSize);
        return ResponseEntity.ok(authors);
    }

    @PostMapping("/assign/{bookId}")
    public ResponseEntity<?> assignBook(@PathVariable Long bookId, @RequestParam("expirationDate")
    @Future @DateTimeFormat(pattern = "dd-MM-yyyy") Date expirationDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = userService.findUserByUsername(username).getId();
        if (itemService.assignBook(bookId, userId, new java.sql.Date(expirationDate.getTime()))) {
            return ResponseEntity.status(HttpStatus.OK).body("Book was assigned");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User has maximum amount of books");
        }
    }

    @PostMapping("/unassign")
    public ResponseEntity<?> unassignBook(@RequestBody ItemDto item) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto user = userService.findUserByUsername(authentication.getName());
        ItemDto dbItem = itemService.findById(item.getId());
        if (!user.getId().equals(dbItem.getUserId()) && !user.getRole().getRole().equals("admin")
                && !user.getRole().getRole().equals("librarian")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Can't unassign item, not enough permission");
        } else {
            itemService.unassignItem(item.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Book was unassigned");
        }
    }

    @GetMapping("/bookid/{bookId}")
    public ResponseEntity<?> getItemsForBook(@PathVariable Long bookId,
                                             @RequestParam(name = "page", defaultValue = "1") int pageNumber,
                                             @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Page<ItemDto> items = itemService.getItemsForBook(bookId, pageNumber, pageSize);
        return ResponseEntity.ok().body(items);
    }
}
