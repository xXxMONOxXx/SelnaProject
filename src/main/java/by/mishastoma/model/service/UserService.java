package by.mishastoma.model.service;

import by.mishastoma.model.dto.DTORole;
import by.mishastoma.model.dto.DTOUser;

import java.util.List;

public interface UserService extends CrudService<DTOUser> {

    DTOUser findUserByIdCriteria(Integer id);

    DTOUser findUserByUsername(String username);

    List<DTOUser> findUsersWithRole(DTORole role);
}
