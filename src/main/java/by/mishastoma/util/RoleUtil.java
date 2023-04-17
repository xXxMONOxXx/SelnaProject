package by.mishastoma.util;

import by.mishastoma.web.dto.RoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleUtil {

    @Value("${default.role.id}")
    private Long ID;
    @Value("${default.role}")
    private String ROLE;

    public RoleDto createDefaultRole() {
        return RoleDto.builder()
                .id(ID)
                .role(ROLE)
                .build();
    }
}
