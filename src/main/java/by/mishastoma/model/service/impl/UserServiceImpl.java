package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dto.RoleDto;
import by.mishastoma.model.dto.UserDto;
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
    public void insert(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        dao.save(user);
    }

    @Transactional
    @Override
    public void delete(Serializable id) {
        User entity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + id));
        dao.delete(entity);
    }

    @Override
    public UserDto findById(Serializable id) {
        User userEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public void update(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        dao.update(user);
    }

    @Override
    public UserDto findUserByIdCriteria(Serializable id) {
        User userEntity = dao.findByIdCriteria(id).orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User userEntity = dao.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Can't find user with username " + username));
        return modelMapper.map(dao.findByUsername(username), UserDto.class);
    }

    @Override
    public List<UserDto> findUsersWithRole(RoleDto role) {
        List<User> userEntities = dao.findByRole(modelMapper.map(role, Role.class));
        return userEntities.stream().map(x -> modelMapper.map(x, UserDto.class)).toList();
    }
}
