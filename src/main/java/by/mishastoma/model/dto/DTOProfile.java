package by.mishastoma.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DTOProfile {
    private Integer id;
    private String firstname;
    private String surname;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private boolean isBlocked;
}
