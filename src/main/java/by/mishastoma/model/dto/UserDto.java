package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {

    private Long id;
    private Boolean isBlocked;
    private String username;
    private String password;
    private RoleDto role;
    private ProfileDto profile;
    private List<ItemDto> items;
}
