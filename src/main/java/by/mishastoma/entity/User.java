package by.mishastoma.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
public class User extends AbstractEntity {

    private String username;
    private String password;
    private Role role;
    private String firstname;
    private String surname;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private boolean isBlocked;
    private List<Long> itemIds;
}
