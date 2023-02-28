package by.mishastoma.controller;

import by.mishastoma.model.dto.GenreDto;
import by.mishastoma.model.dto.RoleDto;
import by.mishastoma.model.dto.UserDto;
import by.mishastoma.model.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements CrudController<UserDto> {
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) {
        try {
            UserDto userDto = objectMapper.readValue(obj, UserDto.class);
            userService.insert(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        UserDto user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public void update(String obj) {
        try {
            UserDto userDto = objectMapper.readValue(obj, UserDto.class);
            userService.update(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserByIdCriteria(Long id) {
        try {
            UserDto user = userService.findUserByIdCriteria(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserByUserName(String username) {
        try {
            UserDto user = userService.findUserByUsername(username);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserWithRole(String role) {
        try {
            RoleDto roleDto = objectMapper.readValue(role, RoleDto.class);
            List<UserDto> list = userService.findUsersWithRole(roleDto);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
