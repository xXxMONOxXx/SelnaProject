package by.mishastoma.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DTOUser {

    private long id;
    private String username;
    private String password;
    private String role;
    private String firstname;
    private String surname;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private boolean isBlocked;
    private long[] itemIds;
}
