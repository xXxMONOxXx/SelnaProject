package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private Boolean isBlocked;
    @Size(max = 32, min = 3, message = "Username maximum size is 32 and minimum is 3")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Invalid username")
    private String username;
    @Size(min = 6, max = 64, message = "Password's length is shorter than 6 or longer than 64")
    private String password;
    private RoleDto role;
    private ProfileDto profile;
    private List<ItemDto> items;
}
