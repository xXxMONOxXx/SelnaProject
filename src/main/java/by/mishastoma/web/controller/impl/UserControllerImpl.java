package by.mishastoma.web.controller.impl;

import by.mishastoma.service.UserService;
import by.mishastoma.web.controller.CrudController;
import by.mishastoma.web.dto.UserDto;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements CrudController<UserDto> {
    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDto user) {
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody UserDto user, @PathVariable Long id) {
        user.setId(id);
        userService.update(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<UserDto> findUserByUserName(@RequestParam String username) {
        UserDto user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}
