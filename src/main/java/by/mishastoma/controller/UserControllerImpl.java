package by.mishastoma.controller;

import by.mishastoma.model.dto.RoleDto;
import by.mishastoma.model.dto.UserDto;
import by.mishastoma.model.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserControllerImpl implements CrudController {
    private final UserService service;
    private final ObjectMapper objectMapper;

    private UserControllerImpl(UserService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void insert(String obj) {
        try {
            UserDto userDto = objectMapper.readValue(obj, UserDto.class);
            service.insert(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }

    @Override
    public String findById(Long id) {
        try {
            UserDto user = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            UserDto userDto = objectMapper.readValue(obj, UserDto.class);
            service.update(userDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserByIdCriteria(Long id) {
        try {
            UserDto user = service.findUserByIdCriteria(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserByUserName(String username) {
        try {
            UserDto user = service.findUserByUsername(username);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserWithRole(String role) {
        try {
            RoleDto roleDto = objectMapper.readValue(role, RoleDto.class);
            List<UserDto> list = service.findUsersWithRole(roleDto);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
