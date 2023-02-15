package by.mishastoma.model.service;

import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.dto.RoleDto;

import java.io.Serializable;
import java.util.List;

public interface UserService extends CrudService<DTOUser> {

    DTOUser findUserByIdCriteria(Serializable id);

    DTOUser findUserByUsername(String username);

    List<DTOUser> findUsersWithRole(RoleDto role);
}
