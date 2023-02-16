package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ProfileDto {
    private Long id;
    private String firstname;
    private String surname;
    private String phone;
    private String email;
    private Date birthdate;
}
