package by.mishastoma.controller;

import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserControllerImpl implements CrudController {
    private final UserService service;
    private final ObjectMapper objectMapper;

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
    public String findAll() {
        try {
            List<DTOUser> list = service.findAll();
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
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
}
