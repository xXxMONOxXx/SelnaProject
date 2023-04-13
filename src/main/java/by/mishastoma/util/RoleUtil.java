package by.mishastoma.util;

import by.mishastoma.web.dto.RoleDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RoleUtil {

    private Long ID = 1L;
    private String ROLE = "user";

    public RoleDto createDefaultRole() {
        return RoleDto.builder()
                .id(ID)
                .role(ROLE)
                .build();
    }
}
