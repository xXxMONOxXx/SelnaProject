package by.mishastoma.service.impl;

import by.mishastoma.dao.UserDao;
import by.mishastoma.dto.DTOUser;
import by.mishastoma.entity.User;
import by.mishastoma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final ModelMapper modelMapper;

    @Override
    public void insert(DTOUser dtoUser) {
        User user = modelMapper.map(dtoUser, User.class);
        dao.insert(user);
    }

    @Override
    public void delete(DTOUser dtoUser) {
        User user = modelMapper.map(dtoUser, User.class);
        dao.delete(user);
    }

    @Override
    public List<DTOUser> findAll() {
        List<User> users = dao.findAll();
        return users.stream().map(x -> modelMapper.map(x, DTOUser.class)).toList();
    }

    @Override
    public void update(DTOUser dtoUser) {
        User user = modelMapper.map(dtoUser, User.class);
        dao.update(user);
    }
}
