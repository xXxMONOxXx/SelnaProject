package by.mishastoma.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {

    private Long id;
    private Boolean isBlocked;
    private String username;
    private String password;
    private RoleDto role;
    private ProfileDto profile;
    private List<ItemDto> items;
}
