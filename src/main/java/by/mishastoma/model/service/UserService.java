package by.mishastoma.model.service;

import by.mishastoma.model.dto.DTORole;
import by.mishastoma.model.dto.DTOUser;
import by.mishastoma.model.entity.RoleEntity;
import by.mishastoma.model.entity.UserEntity;

import java.util.List;

public interface UserService extends CrudService<DTOUser> {

    DTOUser findUserByIdCriteria(Integer id);

    DTOUser findUserByUsername(String username);

    List<DTOUser> findUsersWithRole(DTORole role);
}
