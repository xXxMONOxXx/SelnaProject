package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.dto.RoleDto;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import by.mishastoma.model.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    private final UserDao dao;
    private final ModelMapper modelMapper;

    private UserServiceImpl(UserDao dao, ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(DTOUser dtoUser) {
        User user = modelMapper.map(dtoUser, User.class);
        dao.save(user);
    }

    @Transactional
    @Override
    public void delete(DTOUser dtoUser) {
        User user = modelMapper.map(dtoUser, User.class);
        User entity = dao.findById(user.getId()).orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + user.getId()));
        dao.delete(entity);
    }

    @Override
    public DTOUser findById(Serializable id) {
        User userEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + id));
        return modelMapper.map(userEntity, DTOUser.class);
    }

    @Override
    public void update(DTOUser dtoUser) {
        User user = modelMapper.map(dtoUser, User.class);
        dao.update(user);
    }

    @Override
    public DTOUser findUserByIdCriteria(Serializable id) {
        User userEntity = dao.findByIdCriteria(id).orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + id));
        return modelMapper.map(userEntity, DTOUser.class);
    }

    @Override
    public DTOUser findUserByUsername(String username) {
        User userEntity = dao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Can't find user with username " + username));
        return modelMapper.map(dao.findByUsername(username), DTOUser.class);
    }

    @Override
    public List<DTOUser> findUsersWithRole(RoleDto role) {
        List<User> userEntities = dao.findByRole(modelMapper.map(role, Role.class));
        return userEntities.stream().map(x -> modelMapper.map(x, DTOUser.class)).toList();
    }
}
