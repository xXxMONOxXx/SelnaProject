package by.mishastoma.model.dao;


import by.mishastoma.model.entity.Role;
import by.mishastoma.model.entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User>{

    Optional<User> findByIdCriteria(Serializable id);

    List<User> findByRole(Role role);

    Optional<User> findByUsername(String username);
}
