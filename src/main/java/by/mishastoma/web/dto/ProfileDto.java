package by.mishastoma.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private Long userId;
    @Size(max = 32, message = "Firstname maximum size is 32")
    @Pattern(regexp = "[A-Za-z]+", message = "Firstname must start with big letter and" +
            " shouldn't contain anything except letters")
    private String firstname;
    @Size(max = 256, message = "Surname maximum size is 256")
    @Pattern(regexp = "[A-Za-z]+", message = "Firstname must start with big letter and" +
            " shouldn't contain anything except letters")
    private String surname;
    @Size(max = 25, message = "Phone number maximum size is 25")
    @Pattern(regexp = "\\d", message = "Phone number must contain only numbers")
    private String phone;
    @Email
    @Size(max = 256, message = "Email maximum size is 256")
    @Pattern(regexp = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", message = "Invalid email")
    private String email;
    @Past(message = "Invalid birthdate, user must be already be born")
    private Date birthdate;
    private UserDto user;
}
