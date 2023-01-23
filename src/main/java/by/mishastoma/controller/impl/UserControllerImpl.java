package by.mishastoma.controller.impl;

import by.mishastoma.controller.UserController;
import by.mishastoma.dto.DTOUser;
import by.mishastoma.exception.ControllerException;
import by.mishastoma.exception.ServiceException;
import by.mishastoma.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService service;
    private final ObjectMapper objectMapper;

    @Override
    public void insert(String obj) throws ControllerException {
        try {
            DTOUser dtoUser = objectMapper.readValue(obj, DTOUser.class);
            service.insert(dtoUser);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void delete(String obj) throws ControllerException {
        try {
            DTOUser dtoUser = objectMapper.readValue(obj, DTOUser.class);
            service.delete(dtoUser);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public String findAll() throws ControllerException {
        try {
            List<DTOUser> list = service.findAll();
            return objectMapper.writeValueAsString(list);

        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }

    @Override
    public void update(String obj) throws ControllerException {
        try {
            DTOUser dtoUser = objectMapper.readValue(obj, DTOUser.class);
            service.update(dtoUser);
        } catch (ServiceException | JsonProcessingException e) {
            throw new ControllerException(e);
        }
    }
}
