package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long id;
    @Size(max = 32, message = "Firstname maximum size is 32")
    private String firstname;
    @Size(max = 256, message = "Surname maximum size is 256")
    private String surname;
    @Size(max = 25, message = "Phone number maximum size is 25")
    private String phone;
    @Email
    @Size(max = 256, message = "Email maximum size is 256")
    private String email;
    @Past(message = "Invalid birthdate, user must be already be born")
    private Date birthdate;
}
