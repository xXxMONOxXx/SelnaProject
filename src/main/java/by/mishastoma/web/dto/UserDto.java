package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private Boolean isBlocked;
    private String username;
    private String password;
    private RoleDto role;
    private ProfileDto profile;
    private List<ItemDto> items;
}
