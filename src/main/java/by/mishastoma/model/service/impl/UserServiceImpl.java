package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.entity.User;
import by.mishastoma.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOUser dtoUser) {
        try {
            User user = modelMapper.map(dtoUser, User.class);
            dao.insert(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(DTOUser dtoUser) {
        try {
            User user = modelMapper.map(dtoUser, User.class);
            dao.delete(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DTOUser> findAll() {
        try {
            List<User> users = dao.findAll();
            return users.stream().map(x -> modelMapper.map(x, DTOUser.class)).toList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(DTOUser dtoUser) {
        try {
            User user = modelMapper.map(dtoUser, User.class);
            dao.update(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
