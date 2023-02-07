package by.mishastoma.model.dao;


import by.mishastoma.model.entity.RoleEntity;
import by.mishastoma.model.entity.UserEntity;

import java.io.Serializable;
import java.util.List;

public interface UserDao {
    void save(UserEntity entity);

    UserEntity findById(Serializable id);

    void update(UserEntity t);

    void delete(UserEntity t);

    UserEntity findByIdCriteria(Integer id);

    List<UserEntity> findUsersByRole(RoleEntity role);

    UserEntity findUserByUsername(String username);
}
