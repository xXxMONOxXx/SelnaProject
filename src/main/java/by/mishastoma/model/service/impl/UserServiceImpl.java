package by.mishastoma.model.service.impl;

import by.mishastoma.model.dao.UserDao;
import by.mishastoma.model.dto.DTORole;
import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.entity.RoleEntity;
import by.mishastoma.model.entity.UserEntity;
import by.mishastoma.model.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
        UserEntity user = modelMapper.map(dtoUser, UserEntity.class);
        dao.save(user);
    }

    @Override
    public void delete(DTOUser dtoUser) {
        UserEntity user = modelMapper.map(dtoUser, UserEntity.class);
        dao.delete(user);
    }

    @Override
    public DTOUser findById(int id) {
        return modelMapper.map(dao.findById(id), DTOUser.class);
    }

    @Override
    public void update(DTOUser dtoUser) {
        UserEntity user = modelMapper.map(dtoUser, UserEntity.class);
        dao.update(user);
    }

    @Override
    public DTOUser findUserByIdCriteria(Integer id) {
        return modelMapper.map(dao.findByIdCriteria(id), DTOUser.class);
    }

    @Override
    public DTOUser findUserByUsername(String username) {
        return modelMapper.map(dao.findUserByUsername(username), DTOUser.class);
    }

    @Override
    public List<DTOUser> findUsersWithRole(DTORole role) {
        List<UserEntity> userEntities = dao.findUsersByRole(modelMapper.map(role, RoleEntity.class));
        return userEntities.stream().map(x -> modelMapper.map(x, DTOUser.class)).toList();
    }
}
