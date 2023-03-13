package by.mishastoma.service.impl;

import by.mishastoma.exception.UserNotFoundException;
import by.mishastoma.model.dao.UserDao;
import by.mishastoma.service.UserService;
import by.mishastoma.web.dto.RoleDto;
import by.mishastoma.web.dto.UserDto;
import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userDao.save(user), UserDto.class);
    }

    @Override
    @Transactional
    public void delete(Serializable id) {
        User user = userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
         userDao.delete(user);
    }

    @Override
    @Transactional
    public UserDto findById(Serializable id) {
        User userEntity = userDao.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    @Transactional
    public void update(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userDao.update(user);
    }

    @Override
    public UserDto findUserByIdCriteria(Serializable id) {
        User userEntity = userDao.findByIdCriteria(id).orElseThrow(() -> new UserNotFoundException(id));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto findUserByUsername(String username) {
        User userEntity = userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> findUsersWithRole(RoleDto role) {
        List<User> userEntities = userDao.findByRole(modelMapper.map(role, Role.class));
        return userEntities.stream().map(x -> modelMapper.map(x, UserDto.class)).toList();
    }
}
