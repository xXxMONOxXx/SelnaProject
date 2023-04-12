package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    @Size(max = 32, message = "Role maximum size is 32")
    @Pattern(regexp="^[A-Za-z]+$", message = "Invalid role")
    private String role;
}
