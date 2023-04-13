package by.mishastoma.service;

import by.mishastoma.web.dto.RoleDto;
import by.mishastoma.web.dto.UserDto;

import java.io.Serializable;
import java.util.List;

public interface UserService extends CrudService<UserDto> {

    UserDto findUserByIdCriteria(Serializable id);

    UserDto findUserByUsername(String username);

    List<UserDto> findUsersWithRole(RoleDto role);

    String signIn(UserDto user);

    void signUp(UserDto user);
}
