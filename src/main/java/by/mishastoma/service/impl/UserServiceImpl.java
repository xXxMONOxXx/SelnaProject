package by.mishastoma.service.impl;

import by.mishastoma.dao.UserDao;
import by.mishastoma.dto.DTOUser;
import by.mishastoma.entity.User;
import by.mishastoma.exception.DaoException;
import by.mishastoma.exception.ServiceException;
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
    public void insert(DTOUser dtoUser) throws ServiceException {
        User user = modelMapper.map(dtoUser, User.class);
        try {
            dao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void delete(DTOUser dtoUser) throws ServiceException {
        User user = modelMapper.map(dtoUser, User.class);
        try {
            dao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<DTOUser> findAll() throws ServiceException {
        try {
            List<User> users = dao.findAll();
            return users.stream().map(x -> modelMapper.map(x, DTOUser.class)).toList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(DTOUser dtoUser) throws ServiceException {
        User user = modelMapper.map(dtoUser, User.class);
        try {
            dao.update(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
