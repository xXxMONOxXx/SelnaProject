package by.mishastoma.model.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class ProfileDto {
    private Long id;
    private String firstname;
    private String surname;
    private String phone;
    private String email;
    private Date birthdate;
}
