package by.mishastoma.model.service;

import by.mishastoma.model.dto.RoleDto;
import by.mishastoma.model.dto.UserDto;

import java.io.Serializable;
import java.util.List;

public interface UserService extends CrudService<UserDto> {

    UserDto findUserByIdCriteria(Serializable id);

    UserDto findUserByUsername(String username);

    List<UserDto> findUsersWithRole(RoleDto role);
}
