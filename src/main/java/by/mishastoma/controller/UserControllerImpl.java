package by.mishastoma.controller;

import by.mishastoma.model.dto.DTORole;
import by.mishastoma.model.dto.DTOUser;
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
            DTOUser dtoUser = objectMapper.readValue(obj, DTOUser.class);
            service.insert(dtoUser);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String obj) {
        try {
            DTOUser dtoUser = objectMapper.readValue(obj, DTOUser.class);
            service.delete(dtoUser);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findById(int id) {
        try {
            DTOUser user = service.findById(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String obj) {
        try {
            DTOUser dtoUser = objectMapper.readValue(obj, DTOUser.class);
            service.update(dtoUser);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserByIdCriteria(Integer id) {
        try {
            DTOUser user = service.findUserByIdCriteria(id);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserByUserName(String username) {
        try {
            DTOUser user = service.findUserByUsername(username);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String findUserWithRole(String role) {
        try {
            DTORole dtoRole = objectMapper.readValue(role, DTORole.class);
            List<DTOUser> list = service.findUsersWithRole(dtoRole);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
